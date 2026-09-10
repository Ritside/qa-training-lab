\# QA Training Lab



Practice repository for applying ISTQB Foundations concepts in a real-world

testing environment, covering different test levels and types, with automation

integrated into a cross-platform CI/CD pipeline (Windows + Linux).



\## Work Environments

\- \*\*Windows 11 Pro\*\* (VM on VMware Workstation Pro)

\- \*\*Ubuntu Server LTS\*\* (VM on VMware Workstation Pro)



\## Repository Structure



| Folder | Test Level/Type | Tools |

|---|---|---|

| `unit-tests/` | Component/Unit Testing | pytest, Jest, JUnit |

| `integration-tests/` | Integration Testing | Postman, Newman |

| `system-tests/` | System Testing (E2E) | Playwright (JS/Python/Java), Selenium (JS/Python/Java) |

| `acceptance-tests/` | Acceptance Testing (BDD/ATDD) | Cucumber-JVM (Gherkin/BDD) |

| `performance-tests/` | Non-Functional (Performance) | \_(pending — Phase 6)\_ |

| `security-tests/` | Non-Functional (Security) | \_(pending — Phase 6)\_ |



\## CI/CD

Pipeline configured with GitHub Actions (`.github/workflows/`), running on

both Windows and Linux runners. \_(pending — Phase 8)\_



\## Progress

\- \[x] Phase 1 — Environment setup (Windows + Linux)

\- \[x] Phase 2 — Version control and repo structure

\- \[X] Phase 3 — Unit Testing

\- \[X] Phase 4 — Integration Testing

\- \[X] Phase 5 — System/Acceptance Testing (TDD/BDD/ATDD)

\- \[ ] Phase 6 — Non-Functional Testing

\- \[ ] Phase 7 — Regression/Maintenance Testing

\- \[ ] Phase 8 — CI/CD Pipeline

\- \[ ] Phase 9 — Final integrative project

