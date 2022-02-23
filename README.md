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

| USER                    | ADMINISTRATOR           |
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

![EntityModelsDiagram](http://www.plantuml.com/plantuml/png/bP9DJiCm48NtEOML9L8NtNQ1-XEwew2M7i0aGsHaZv5d0bI8k-EwRjtKHD1TylozDp-QAR5qt1bTr5eIYIs1-og44E4BfO69sB1Js0RnqJCVrZKzySv8fK_ATn6ZPerHg8YTPEi4V39W4miaQCDT0yX3prjKx-18yUxGrEgoeONjaDskhtayRwU2rW4Cn2nbtMdlkZHjkkTNg7cuk2ClpLLRGQsCr45-U6DbKMh_OAq6YIbl3UGzDT10OWLKEnNy-GGhoq1nKrdKKcd8BKz0VALbHgHQD83N6jUI_NyWudUscOKr--1pRt6yW79SYj58b3TbAZSHzn9b2oHkV-LYbGI5czk_UKJ32qSmdT7VS3oKVu3nE0R8R9GADF005GT9o44vCUDUqXoqnJPWHp6He_FE4XcboFK6JT11YKoskSHD0PlEw1y0)

