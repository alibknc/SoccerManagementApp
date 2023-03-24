# Soccer Management
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

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
* Factory Pattern

### APIs :memo:
| API                                              | Method | Açıklama                                       |
|--------------------------------------------------|--------|---------------------------------------------------|
| /players                                  | GET    | Tüm oyuncuların listesini döndürür                                |
| /players/{teamId}                     | GET    | İstenen takıma ait oyuncuları döndürür                       |
| /players                                  | POST   | Yeni bir oyuncu oluşturur                            |
| /players/{teamId}                     | DELETE | Bir takımdaki tüm oyuncuları siler                       |
| /teams                              | GET    | Tüm takımların listesini döndürür                            |
| /teams/{id}               | GET    | İstenen takımı döndürür                   |
| /teams                               | POST   | Yeni bir takım oluşturur                        |
| /teams               | PUT    | Mevcut bir takımı günceller                   |
| /teams/{id}               | DELETE | İstenen takımı oyuncuları ile birlikte siler                   |

## Kullanmadan Önce

Bu projeyi çalıştırmak için bir PostgreSQL veritabanı oluşturmanız ve [application.properties](https://github.com/alibknc/SoccerManagementApp/blob/master/src/main/resources/application.properties) dosyasında bulunan aşağıdaki alanları güncellemeniz gerekmektedir.

`spring.datasource.url`

`spring.datasource.username`

`spring.datasource.password`

### Postman Collection :pushpin:
[Click Here](static/stock-management.postman_collection.json)

### License :key:
Distributed under the MIT License. See [LICENSE](LICENSE) for more information.
