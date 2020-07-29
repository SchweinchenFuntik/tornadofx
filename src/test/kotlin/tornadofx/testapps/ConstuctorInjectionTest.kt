package tornadofx.testapps

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

class TView(private val tController: TController, val personModel: PersonModel) : View("Constructor Injection") {
    override val root = vbox(10) {
        paddingAll = 5
        textfield(personModel.name)

        listview(tController.persons) { cellFormat { text = it.name } }
    }
}

class TController(val personModel: PersonModel) : Controller() {
    val persons = observableListOf(
            Person().apply { name = "Bob" },
            Person().apply { name = "Vanga" }
    )

    init {
        personModel.item = persons.first()
    }
}

class Person {
    val idProperty = SimpleIntegerProperty(0)
    var id by idProperty

    val nameProperty = SimpleStringProperty("")
    var name by nameProperty
}

class PersonModel : ItemViewModel<Person>() {
    val id = bind(Person::idProperty)
    val name = bind(Person::nameProperty)
}



