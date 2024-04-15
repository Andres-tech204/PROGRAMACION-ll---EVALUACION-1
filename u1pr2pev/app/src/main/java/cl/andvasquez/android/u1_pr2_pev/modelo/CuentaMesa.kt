package cl.andvasquez.android.u1_pr2_pev.modelo

class CuentaMesa(val numeroMesa: Int) {
    private val items: MutableList<ItemMesa> = mutableListOf()
    var aceptaPropina: Boolean = true

    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        val itemMesa = ItemMesa(itemMenu, cantidad)
        agregarItem(itemMesa)
    }

    fun agregarItem(itemMesa: ItemMesa) {
        items.add(itemMesa)
    }

    fun calcularTotalSinPropina(): Int {
        var total = 0
        for (item in items) {
            total += item.calcularSubtotal()
        }
        return total
    }

    fun calcularPropina(): Int {
        return (calcularTotalSinPropina() * 0.1).toInt()
    }

    fun calcularTotalConPropina(): Int {
        return calcularTotalSinPropina() + if (aceptaPropina) calcularPropina() else 0
    }
}
class ItemMesa(val itemMenu: ItemMenu, var cantidad: Int) {
    fun calcularSubtotal(): Int {
        return itemMenu.precio * cantidad
    }
}
class ItemMenu(val nombre: String, val precio: Int) {
    constructor(nombre: String, precio: String) : this(nombre, precio.toInt())
}

