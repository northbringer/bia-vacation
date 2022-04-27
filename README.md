# bia-vacation

<br>

## Naming conventions ##
Текущие соглашения о наименованиях в проекте.

---
### Наименования полей классов, связанных с UI ###
При именовании полей классов, связанных с UI-элементами релевантного Layout, в начале указывается **имя класса**, 
далее **семантика назначения поля**, далее **тип UI-элемента**.

В примере ниже: 

diagramShowFiltersButton
- *diagram* - имя класса, в котором определяется поле
- *ShowFilters* - семантика назначения поля
- *Button* - тип UI-элемента (примеч. не смотря на то, что в коде тип переменной - ImageView, в названии все равно указывается Button, 
т.к. для пользователя, взаимодействующего с UI, это является своеобразной кнопкой.


![naming_class_id](https://user-images.githubusercontent.com/91637899/165583297-0be96e8d-1bd1-4bd7-afeb-477ed0e821b0.png)

---
### Наименования View ID в XML-файлах ###
View ID именуются в snake_case. В начале указывается *наименование XML-файла*, в котором располагается View, 
далее указывается *тип View*, далее *семантика его назначения*.

Пример:

*Файл* - fragment_filter.xml. <br>
*View* - ImageView, по нажатию на которую открывается следующий фрагмент (fragment_period_filter.xml).<br><br>
*Корректное наименование View ID:* **android:id="@+id/filter_button_open_period_filter"**. <br>(примеч. причина указания типа, как "button", рассмотрена выше)

---
### Наименования ресурсов (Android Resources) ###
