# Soccer Management
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

![Service Layer Test Code Coverage](https://img.shields.io/badge/coverage-97%25-green)

``` 
Uygulama 2 ana endpoint'ten oluşmaktadır. Team ve Player.
* Her takımın bir adı ve oyuncuları vardır.
* Her oyuncunun bir adı, bir statüsü, bir pozisyonu ve bir takımı vardır.
``` 

### Tech Stack & Patterns :dart:
* Java 17
* Spring Boot
* Maven
* Hibernate
* Mapstruct  
* Junit
* Mockito
* Factory Pattern

### APIs :memo:
| API                                              | Method | Açıklama                                          |
|--------------------------------------------------|--------|---------------------------------------------------|
| /players                                         | GET    | Tüm oyuncuların listesini döndürür                |
| /players/{id}                                    | GET    | İstenen oyuncuya ait bilgileri döndürür           |
| /players                                         | POST   | Yeni bir oyuncu oluşturur                         |
| /players                                         | PUT    | Mevcut bir oyuncuyu günceller                     |
| /players/{id}                                    | DELETE | İstenen oyuncuyu siler                            |
| /teams                                           | GET    | Tüm takımların listesini döndürür                 |
| /teams/{id}                                      | GET    | İstenen takımı döndürür                           |
| /teams/{id}/players                              | GET    | İstenen takıma ait oyuncuları döndürür            |
| /teams                                           | POST   | Yeni bir takım oluşturur                          |
| /teams                                           | PUT    | Mevcut bir takımı günceller                       |
| /teams/{id}                                      | DELETE | İstenen takımı oyuncuları ile birlikte siler      |
| /teams/{id}/players                              | DELETE | İstenen takımın yalnızca oyuncularını siler       |

## Kullanmadan Önce

Bu projeyi çalıştırmak için bir PostgreSQL veritabanı oluşturmanız ve [application.properties](https://github.com/alibknc/SoccerManagementApp/blob/master/src/main/resources/application.properties) dosyasında bulunan aşağıdaki alanları güncellemeniz gerekmektedir.

`spring.datasource.url`

`spring.datasource.username`

`spring.datasource.password`

### Postman Collection :pushpin:
[Click Here](soccer-management.postman_collection.json)

### License :key:
Distributed under the MIT License. See [LICENSE](LICENSE) for more information.
