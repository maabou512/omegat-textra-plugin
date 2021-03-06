# Change Log
All notable changes to this project will be documented in this file.

## [Unreleased]

## [0.7] - 2019-08-09
#**## Fixed
- Fix configuration doesn't refrect and cause exception.(#13)

## [0.6] - 2019-05-06
### Add
- Unit test of options for Chinese languages.

### Changed
- Updated a supported language combination list
- Update build.gradle for gradle 4.8 and 5.4
- Update a referenced OmegaT version to 4.1
- Use Spotbugs instead of FindBugs for QA
- OmegatTextraMachineTranslation class now inherited from BaseTranslate

### Fixed
- Fix formatLang() internal function handle chinese not to break;

## [0.5] - 2018-11-10
### Changed
- There are general, patent and patent claim mode in option dialog
- Update Gradle version 4.8 to support OpenJDK10

### Fixed
- Catch up Web API change on TexTra.
- Fix checkstyle warnings.

## [0.4] - 2016-11-03
### Add
- Unit tests

### Changed
- Refactoring class design.
- Introduce TextraApiClient class.
- Ignore exception if fails launching web browser on desktop.

### Fixed
- Method name typo: Not found unloadPlugin method when exit.
- Build system typo for integration test.


## [0.3] - 2016-10-31
### Add
- Mode and language support validation check.

### Changed
- Use SLF4J for error message logging.

### Fixed
- Generic/JPO patent mode made exception with enum value.
- Coding style improvement.
- Potential NPE with Cache handling.


## [0.2] - 2016-10-29
### Add
- Configuration dialog for TexTra API key, secret and mode.

### Changed
- README: Remove old TexTra terms and clauses for limitations
  TexTra updates its Terms.

### Fixed
- Save configurations to OmegaT preference.


## 0.1 - 2016-09-03
### Added
- Start project.

[Unreleased]: https://github.com/miurahr/omegat-markdown-plugin/compare/v0.7...HEAD
[0.7]: https://github.com/miurahr/omegat-markdown-plugin/compare/v0.6...v0.7
[0.6]: https://github.com/miurahr/omegat-markdown-plugin/compare/v0.5...v0.6
[0.5]: https://github.com/miurahr/omegat-markdown-plugin/compare/v0.4...v0.5
[0.4]: https://github.com/miurahr/omegat-markdown-plugin/compare/v0.3...v0.4
[0.3]: https://github.com/miurahr/omegat-markdown-plugin/compare/v0.2...v0.3
[0.2]: https://github.com/miurahr/omegat-markdown-plugin/compare/v0.1...v0.2
