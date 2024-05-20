###Задание:
Выберите кейс. Кейс можно взять стандартный интернет-магазин или выбрать одну из архитектурных кат Нила Форда: https://nealford.com/katas/list.html
Для выбранного кейса используйте один из паттерных декомпозиций и предложите вариант разбиения на микросервисы
###В качестве решения предоставьте:
- Пользовательские сценарии в свободном формате. 
- Общую схему взаимодействия сервисов в свободном формате. 
- Для каждого сервиса опишите назначение сервиса и его зону ответственности в свободном формате. 
- Опишите контракты взаимодействия сервисов друг с другом в свободном формате.


###Решение:
Кейс Where's Fluffymoon? https://nealford.com/katas/kata?id=WheresFluffy 
####Пользовательский сценарий:
- Есть авторизованный клиент
- Клиент просматривает список потерянных животных рядом с его локацией
- Клиент нажимает на карточку с потерянным животным, чтобы просмотреть информацию о нём
- Клиент нажимает на кнопку "Питомец найден", прикладывает фотографию в качестве доказательства, что питомец у него
- Карточка питомца становится неактивна после подтверждения нахождения питомца у клиента-хозяина животного

####Общая схема взаимодействия сервисов:
![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/landauleo/otus-micro/master/UML/task1.puml)

####Назначение сервиса и зона ответственности:
- **Authentication Service (Сервис аутентификации)**: Управляет аутентификацией пользователей
- **Location Service (Сервис геолокации)**: Определяет и управляет локацией клиентов
- **Pets Service (Сервис животных)**: Управляет списком потерянных животных, их деталями и процессом подтверждения 
  нахождения
- **Notification Service (Сервис уведомлений)**: Отправляет уведомления

####Контракты взаимодействия сервисов:

**Authentication Service (Сервис аутентификации)**

Запрос аутентификации пользователя

**HTTP Method**: POST  
**Endpoint**: `/api/auth/login`  
**Request Body**:
```json
{
  "username": "string",
  "password": "string"
}
```
**Response**:
```json
{
  "token": "string",
  "expiresIn": "number"
}
```
**Description**: Аутентифицирует пользователя и возвращает токен доступа.

**Location Service (Сервис геолокации)**

Запрос текущей локации

**HTTP Method**: GET  
**Endpoint**: `/api/location/current`  
**Headers**:
```http
Authorization: Bearer <token>
```
**Response**:
```json
{
  "latitude": "number",
  "longitude": "number"
}
```
**Description**: Возвращает текущую локацию пользователя.

**Pets Service (Сервис животных)**

Запрос списка потерянных животных

**HTTP Method**: GET  
**Endpoint**: `/api/pets/lost`  
**Headers**:
```http
Authorization: Bearer <token>
```
**Query Parameters**:
```http
latitude=<number>&longitude=<number>
```
**Response**:
```json
{
  "pets": [
    {
      "id": "string",
      "name": "string",
      "species": "string",
      "breed": "string",
      "description": "string",
      "lastSeenLocation": {
        "latitude": "number",
        "longitude": "number"
      },
      "dateLost": "string"
    }
  ]
}
```
**Description**: Возвращает список потерянных животных в указанной локации.

Запрос деталей потерянного животного

**HTTP Method**: GET  
**Endpoint**: `/api/pets/lost/{petId}`  
**Headers**:
```http
Authorization: Bearer <token>
```
**Response**:
```json
{
  "id": "string",
  "name": "string",
  "species": "string",
  "breed": "string",
  "description": "string",
  "lastSeenLocation": {
    "latitude": "number",
    "longitude": "number"
  },
  "dateLost": "string"
}
```
**Description**: Возвращает детали потерянного животного по его идентификатору.

Подтверждение нахождения питомца

**HTTP Method**: POST  
**Endpoint**: `/api/pets/found`  
**Headers**:
```http
Authorization: Bearer <token>
```
**Request Body**:
```json
{
  "petId": "string",
  "photoUrl": "string"
}
```
**Response**:
```json
{
  "status": "success",
  "message": "Pet marked as found."
}
```
**Description**: Подтверждает нахождение питомца и делает его карточку неактивной.

**Notification Service (Сервис уведомлений)**

Отправка уведомления о найденном питомце

**HTTP Method**: POST  
**Endpoint**: `/api/notifications/pet-found`  
**Headers**:
```http
Authorization: Bearer <token>
```
**Request Body**:
```json
{
  "petId": "string",
  "ownerId": "string",
  "interestedParties": ["string"]
}
```
**Response**:
```json
{
  "status": "success",
  "message": "Notifications sent."
}
```
**Description**: Отправляет уведомления владельцу питомца и другим заинтересованным сторонам.
