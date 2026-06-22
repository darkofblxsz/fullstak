# Upgrade Plan: fullstak (20260620235413)

- **Generated**: 2026-06-20 23:54:13
- **HEAD Branch**: N/A (git not available)
- **HEAD Commit ID**: N/A (git not available)

## Available Tools

**JDKs**
- JDK 21.0.10: C:\Users\matua\AppData\Local\jdks\jdk-21.0.10\bin (installed, target version)

**Build Tools**
- Maven 3.9.16: C:\Users\matua\.maven\maven-3.9.16\bin (installed)
- Maven Wrapper: mvnw (available in each module)

## Guidelines

> Note: This project is a multi-module Spring Boot 3.3.5 microservices application that is already configured for Java 21. The upgrade ensures all components are optimized and compatible with Java 21 LTS.

## Options

- Working branch: appmod/java-upgrade-20260620235413
- Run tests before and after the upgrade: true

## Upgrade Goals

- **Target Java Version**: Java 21 (LTS - Long-Term Support)
- **Current Java Version**: 21 (configured)
- **Goal**: Ensure full Java 21 compatibility, verify all modules build and test successfully

## Technology Stack

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
|------------------------|---------|-----------------|------------------|
| Java | 21 | 21 | Target version |
| Spring Boot | 3.3.5 | 3.3.5 | Requires Java 17+, compatible with Java 21 ✓ |
| Spring Cloud | 2023.0.3 | 2023.0.3 | Compatible with Java 21 ✓ |
| Maven | 3.9.16 | 3.9.0 | 3.9+ recommended for Java 21 ✓ |
| maven-compiler-plugin | 3.11+ (via SB parent) | 3.11 | Recommended for Java 21 ✓ |
| Lombok | 1.18.38 | 1.18.20 | Compatible with Java 21 ✓ |
| springdoc-openapi | 2.6.0 | 2.0+ | Compatible with Java 21 ✓ |
| Jakarta EE | 10 (SB 3.3) | 10 | Already using Jakarta (javax → jakarta migration complete) ✓ |

## Derived Upgrades

No additional upgrades required:
- Spring Boot 3.3.5 already provides Jakarta EE 10 support ✓
- Java 21 is fully supported by current dependency versions ✓
- Maven 3.9.16 fully supports Java 21 ✓
- All modules inherit configuration from parent POM ✓

## Impact Analysis

### Subsection: Dependency Changes

All core dependencies are already compatible with Java 21. No version changes required to build files.

| Module | Dependency | Current | Action | Target | Reason |
|--------|-----------|---------|--------|--------|--------|
| Parent POM | java.version | 21 | no-change | 21 | Already configured for target |
| Parent POM | maven.compiler.source | 21 | no-change | 21 | Already configured for target |
| Parent POM | maven.compiler.target | 21 | no-change | 21 | Already configured for target |
| Parent POM | spring-boot-starter-parent | 3.3.5 | no-change | 3.3.5 | Compatible with Java 21 |
| Parent POM | spring-cloud-dependencies | 2023.0.3 | no-change | 2023.0.3 | Compatible with Java 21 |

### Subsection: Source Code Changes

The project already uses:
- Jakarta EE namespaces (jakarta.*) instead of deprecated javax.*
- Spring Boot 3.3.5 conventions
- Compatible annotations

No source code refactoring required.

### Subsection: Configuration Changes

Application configuration files are already compatible with Java 21. No config property changes required.

### Subsection: CI/CD Changes

No CI/CD configuration files (Dockerfile, GitHub Actions, etc.) detected in the workspace. If CI/CD exists externally, ensure the Java version specification matches 21.

### Subsection: Risks & Warnings

- **Module Integration**: Verify all 12 micromodules (eureka-server, api-gateway, ms-boleta, ms-carrito, ms-categoria, ms-cliente, ms-envio, ms-pago, ms-pedido, ms-producto, ms-proveedor, ms-stock) compile and test successfully together at the reactor level.
- **Transitive Dependencies**: Some transitive dependencies may have optional updates available; CVE scan will identify critical security updates needed.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Verify Java 21 JDK and Maven 3.9.16 are available and functional for the build
  - **Changes to Make**: Confirm tools are installed; configure environment for build execution
  - **Verification**: `java -version` shows Java 21, `mvn -version` shows Maven 3.9.16

- Step 2: Baseline Build & Tests (Java 21)
  - **Rationale**: Establish baseline: compile all modules and run all tests with Java 21 configuration to document current state
  - **Changes to Make**: No code changes; build verification only
  - **Verification**: Command: `mvn clean test-compile && mvn clean test` from root. Expected: Build succeeds, all tests pass or document known failures

- Step 3: Verify Multi-Module Reactor Build
  - **Rationale**: Ensure reactor-level build consistency across all 12 modules; verify inter-module dependencies resolve correctly
  - **Changes to Make**: No code changes; verify reactor configuration in parent POM
  - **Verification**: Command: `mvn clean verify` from root (includes compile, test, packaging). Expected: All modules compile, all tests pass, all JARs package successfully

- Step 4: CVE Validation & Security Update Check
  - **Rationale**: Scan all dependencies for known CVEs; upgrade any vulnerable transitive dependencies to patched versions
  - **Changes to Make**: If CVEs detected, upgrade dependency versions in pom.xml; if none, no changes needed
  - **Verification**: `mvn dependency:list` extract direct deps; rescan after any updates. Expected: No critical/high CVEs remaining

- Step 5: Final Validation
  - **Rationale**: Confirm full Java 21 compliance, clean build state, and all tests passing
  - **Changes to Make**: Clean all artifacts, rebuild from scratch
  - **Verification**: Command: `mvn clean verify -DskipTests=false`. Expected: 100% test pass rate, all modules package successfully

---

## Version Control Notice

This upgrade is being performed without git version control. Changes remain uncommitted in the working directory. When complete, review changed files manually and commit them to your version control system.
