# UniCloud

Template for a Spring Boot project including Spring REST, HATEOAS, JPA, etc. Additional details: [HELP.md](HELP.md)

[![Open Issues](https://img.shields.io/github/issues-raw/UdL-EPS-SoftArch/UniCloud-API?logo=github)](https://github.com/orgs/UdL-EPS-SoftArch/projects/13)
[![CI/CD](https://github.com/UdL-EPS-SoftArch/UniCloud-API/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/UdL-EPS-SoftArch/UniCloud-API/actions)
[![CucumberReports: UdL-EPS-SoftArch](https://messages.cucumber.io/api/report-collections/faed8ca5-e474-4a1a-a72a-b8e2a2cd69f0/badge)](https://reports.cucumber.io/report-collections/faed8ca5-e474-4a1a-a72a-b8e2a2cd69f0)
[![Heroku App Status](https://heroku-shields.herokuapp.com/unicloud-api)](https://unicloud-api.herokuapp.com)

## Vision

**For** Students **who** want to access different educational resources to help with their College studies 
**the project** UniCloud **is a** educational resources sharing site
**that** allows students to search, rate, and share notes, tests or assignments.
**Unlike** other resource sharing sites, it is open-source.

## Features per Stakeholder

| STUDENT                 | ADMINISTRATOR           |
|-------------------------|-------------------------|
| Register                | Login                   |
| Login                   | Logout                  |
| Logout                  | Delete Rating           |
| Create Rating           | Create University       |
| Modify Rating           | Modify University       |
| Delete Rating           | Delete University       |
| Create Notes            | Create Degree           |
| Modify Notes            | Modify Degree           |
| Delete Notes            | Delete Degree           |
| Create Exam             | Create Subject          |
| Modify Exam             | Modify Subject          |
| Delete Exam             | Delete Subject          |
| Create Practice         | Search by University    |
| Modify Practice         | Search by Subject       |
| Delete Practice         | Search by Degree        |
| Search by University    | Search by Resource Name |
| Search by Subject       |                         |
| Search by Degree        |                         |
| Search by Resource Name |                         |


## Entities Model

![EntityModelsDiagram](http://www.plantuml.com/plantuml/svg/5Sr1gi8m40RW_Jl5ym39x5srI1U2Gg7q034PpK0oASa_U7rrzIs_QI1qM2r_9z5OBB7ryf-1Ovo9UdZxbo3RmmRts1IiyB5LsjFDBUlcjlR4t6EcNULIcYKthrUgGPGMXK1ut5lYue_VCR6chny0?v1)

