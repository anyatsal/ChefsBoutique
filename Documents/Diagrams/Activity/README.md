# Содержание
1. [Авторизация в приложении](#1)
2. [Регистрация в приложении](#2)
3. [Просмотр рецептов пользователя](#3)
4. [Создание нового рецепта](#4)
5. [Поиск рецепта по названию](#5)
6. [Поиск рецепта по ингредиентам](#6)


### 1. Авторизация в приложении<a name="1"></a>
При открытии приложения, пользователь видит стартовое окно, где ему предлагают пройти авторизацию. После ввода данных и нажатия кнопки "Login", данные проверяются на корректность. Если данные введены правильно, то приложение предоставляет доступ к основным функциям, если возникли ошибки, то приложение сообщает об этом.

![Авторизация в приложении](https://raw.githubusercontent.com/anyatsal/ChefsBoutique/master/Documents/Diagrams/Activity/Images/Authorization.png)

### 2. Регистрация в приложении<a name="2"></a>
При нажатии кнопки регистрации, пользователь видит окно регистрации. После ввода данных и нажатия кнопки "Sign up", если регистрация пройдена успешно, то приложение предоставляет доступ к основным функциям, если возникли ошибки, то приложение сообщает об этом.

![Регистрация в приложении](https://raw.githubusercontent.com/anyatsal/ChefsBoutique/master/Documents/Diagrams/Activity/Images/Registration.png)
  
### 3. Просмотр рецептов пользователя<a name="3"></a>
При входе в приложение отображаются сохраненные рецепты пользователя.

![Просмотр рецептов пользователя](https://raw.githubusercontent.com/anyatsal/ChefsBoutique/master/Documents/Diagrams/Activity/Images/ShowUserRecipeList.png)

### 4. Создание нового рецепта<a name="4"></a>
При нажатии кнопки создания нового рецепта, пользователь видит окно создания рецепта. После ввода данных и нажатия кнопки сохранения, рецепт сохраняется в базу данных. Если возникли ошибки, то приложение сообщает об этом.

![Создание нового рецепта](https://raw.githubusercontent.com/anyatsal/ChefsBoutique/master/Documents/Diagrams/Activity/Images/AddingRecipe.png)

### 5. Поиск рецепта по названию<a name="5"></a>
После нажатия кнопки поиска, пользователь видит окно поиска рецептов. После ввода названия и нажатия кнопки "Search", приложение отправляет запрос на получение списка рецептов. Если список рецептов получен, то приложение отображает его, если возникли ошибки, то приложение сообщает об этом.

![Поиск рецепта по названию](https://raw.githubusercontent.com/anyatsal/ChefsBoutique/master/Documents/Diagrams/Activity/Images/SearchByName.png)

### 6. Поиск рецепта по ингредиентам<a name="6"></a>
После нажатия кнопки поиска, пользователь видит окно поиска рецептов. После ввода желаемых и нежелательных ингредиентов и нажатия кнопки "Search", приложение отправляет запрос на получение списка рецептов. Если список рецептов получен, то приложение отображает его, если возникли ошибки, то приложение сообщает об этом.

![Поиск рецепта по ингредиентам](https://raw.githubusercontent.com/anyatsal/ChefsBoutique/master/Documents/Diagrams/Activity/Images/SearchByIngredients.png)
