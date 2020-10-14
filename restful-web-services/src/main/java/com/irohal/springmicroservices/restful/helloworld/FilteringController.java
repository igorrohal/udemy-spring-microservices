package com.irohal.springmicroservices.restful.helloworld;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering") // dynamic filtering: only 'field1' and 'field2' returned
    public MappingJacksonValue filtering() {
        final SomeBeanForFiltering someBean = new SomeBeanForFiltering("value1", "value2", "value3");

        final MappingJacksonValue mapping = mappingFilter(someBean, "field1", "field2");
        return mapping;
    }

    @GetMapping("/filtering-list") // dynamic filtering: only 'field3' returned
    public MappingJacksonValue filteringList() {
        final List<SomeBeanForFiltering> someBeans = Arrays.asList(
                new SomeBeanForFiltering("1.1", "1.2", "1.3"),
                new SomeBeanForFiltering("1.1", "1.2", "1.3"));

        final MappingJacksonValue mapping = mappingFilter(someBeans, "field3");
        return mapping;
    }

    private MappingJacksonValue mappingFilter(final Object object, final String... fields) {
        final SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        final FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        final MappingJacksonValue mapping = new MappingJacksonValue(object);
        mapping.setFilters(filters);
        return mapping;
    }

}
