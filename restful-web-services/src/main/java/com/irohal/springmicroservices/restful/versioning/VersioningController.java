package com.irohal.springmicroservices.restful.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    // URI versioning (also with query params another possibility)
    @GetMapping("/v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // versioning via custom headers - misuse of what headers were intented for
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 personHeaderV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 personHeaderV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // versioning via content negotiation
    @GetMapping(value = "/person/produces", produces = "application/vnd.custom.app-v1+json")
    public PersonV1 personProducesV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.custom.app-v2+json")
    public PersonV2 personProducesV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

}
