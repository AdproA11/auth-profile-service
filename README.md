# authentication-profile-service

Darrel Danadyaksa Poli - 2206081995

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=AdproA11_auth-profile-service&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=AdproA11_auth-profile-service)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=AdproA11_auth-profile-service&metric=bugs)](https://sonarcloud.io/summary/new_code?id=AdproA11_auth-profile-service)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=AdproA11_auth-profile-service&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=AdproA11_auth-profile-service)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=AdproA11_auth-profile-service&metric=coverage)](https://sonarcloud.io/summary/new_code?id=AdproA11_auth-profile-service)

[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=AdproA11_auth-profile-service&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=AdproA11_auth-profile-service)

<details close>
<summary>Clean Code stuffs</summary>

# Sonarcloud

Berikut adalah gambar sonarcloud.

Perhatikan bahwa pada awalnya ada 80 issue. Setelah saya perbaiki, issue nya menjadi 19 (ada 76% peningkatan kode). Pada dasarnya yang saya lakukan adalah menghapus unused import serta tidak menggunakan `@Autowired` karena bermasalah dengan safety dan hidden dependency.

![alt text](images/sonarcloud.png)

Kemudian ini adalah bagian readibility dan maintanibility. Menurut saya sudah cukup baik.

![alt text](images/sonarcloud1.png)

Terakhir, ini adalah overall score saya.

![alt text](images/sonarcloud2.png)

# SOLID

- SRP: JWTGenerator
- OCP: AuthController dapat di extend tanpa modifikasi yang sudah ada
- LSP: Interface AuthenticationManager pada AuthController
- ISP: UserService menyediakan method-method yang diperlukan
- DIP: Pada AuthController, dependency injection dilakukan dengan constructor.

</details>

<details close>
<summary>Architecture & Design Pattern</summary>

# Design Pattern

- DTO Pattern

# Architecture

- Microservice

</details>

<details close>
<summary>Profiling stuffs</summary>

Perhatikan bahwa seluruh test sudah pass

![alt text](images/gambar.png)

</details>
