import kotlin.random.Random

class Carrera (val nombreCarrera: String, val distanciaTotal: Float, val participantes: List<Vehiculo>) {

    val historialAcciones: MutableMap<String, MutableList<String>> = mutableMapOf()
    var estadoCarrera: Boolean = false

    /**
     * Inicia la carrera, estableciendo estadoCarrera a true y comenzando el ciclo de iteraciones donde los vehículos avanzan y realizan acciones.
     */
    fun iniciarCarrera() {
        estadoCarrera = true

        while (estadoCarrera) {

            val vehiculoRandom = participantes.random()
            avanzarVehiculo(vehiculoRandom)
            determinarGanador()
            break
        }
    }


    /**
     * Identificado el vehículo, le hace avanzar una distancia aleatoria entre 10 y 200 km. Si el vehículo necesita repostar, se llama al método repostarVehiculo() antes de que pueda continuar. Este método llama a realizar filigranas.
     */
    fun avanzarVehiculo(vehiculo: Vehiculo) {
        val distanciaARecorrer = ((10..200).random()).toFloat()
        val autonomia = vehiculo.calcularAutonomia()

        if (distanciaARecorrer < 20f && autonomia < distanciaARecorrer) {
            vehiculo.realizaViaje(autonomia)
            añadirAccion(vehiculo, "Ha recorrido $autonomia")
            repostarVehiculo(vehiculo, 0f)
            añadirAccion(vehiculo, "Ha repostado. Tanque lleno")
            vehiculo.realizaViaje(distanciaARecorrer - autonomia)
            añadirAccion(vehiculo, "Ha recorrido ${distanciaARecorrer - autonomia}")
        } else if (distanciaARecorrer >= 20f) {
            var distanciaRecorrida = 0f

            while (distanciaRecorrida < distanciaARecorrer) {
                val numRandom = (1..2).random()

                if (autonomia < 20f) {
                    vehiculo.realizaViaje(autonomia)
                    añadirAccion(vehiculo, "Ha recorrido $autonomia")
                    repostarVehiculo(vehiculo, 0f)
                    añadirAccion(vehiculo, "Ha repostado. Tanque lleno")
                    distanciaRecorrida += autonomia

                } else {
                    val distanciaTramo = if (distanciaARecorrer - distanciaRecorrida >= 20f) 20f else distanciaARecorrer - distanciaRecorrida
                    vehiculo.realizaViaje(distanciaTramo)
                    añadirAccion(vehiculo, "Ha recorrido $distanciaTramo")

                    if (distanciaRecorrida % 20f == 0f) {
                        if (numRandom == 1) {
                            realizarFiligrana(vehiculo)
                        } else {
                            realizarFiligrana(vehiculo)
                            realizarFiligrana(vehiculo)
                        }
                    }
                    distanciaRecorrida += distanciaTramo
                }
            }
        }
        // La distancia es menor a 20 pero tengo suficiente autonomia
        else {
            vehiculo.realizaViaje(distanciaARecorrer)
        }
    }


    /**
     * Añade una accion a un vehiculo en el historial de acciones. Si no existe el nombre, lo crea con una lista.
     * @param vehiculo Objeto de tipo Vehiculo
     * @param Accion String de la accion que se registra en el historial de acciones
     */
    fun añadirAccion(vehiculo: Vehiculo, accion: String){
        // Si no esta en el historial, lo añadimos, y sis esta, solo añadimos la accion
        if (historialAcciones.containsKey(vehiculo.nombre)) {
            historialAcciones[vehiculo.nombre]!!.add(accion)
        } else {
            historialAcciones[vehiculo.nombre] = mutableListOf(accion)
        }
    }



    /**
     * Reposta el vehículo seleccionado, incrementando su combustibleActual y registrando la acción en historialAcciones.
     */
    fun repostarVehiculo(vehiculo: Vehiculo, cantidad: Float){
        vehiculo.repostar(cantidad)

        añadirAccion(vehiculo, "Ha repostado llenando el tanque")
    }


    /**
     * Determina aleatoriamente si un vehículo realiza una filigrana (derrape o caballito) y registra la acción.
     */
    fun realizarFiligrana(vehiculo: Vehiculo) {
        if (vehiculo is Automovil) {
            vehiculo.realizarDerrape()
            añadirAccion(vehiculo, "Ha derrapado. Combustible: ${vehiculo.combustibleActual}")
        }
        else if (vehiculo is Motocicleta) {
            vehiculo.realizarCaballito()
            añadirAccion(vehiculo, "Ha hecho un wheelie. Combustible: ${vehiculo.combustibleActual}")
        }
    }



    /**
     * Revisa posiciones para identificar al vehículo (o vehículos) que haya alcanzado o superado la distanciaTotal, estableciendo el estado de la carrera a finalizado y determinando el ganador.
     */
    fun determinarGanador(){
        var ganador: Vehiculo? = null

        // La mayor distancia encontrada entre todos los vehiculos
        var distanciaMax = 0f

        for (vehiculo in participantes){
            if (vehiculo.kilometrosActuales >= distanciaTotal){
                estadoCarrera = false
            }

            if (vehiculo.kilometrosActuales > distanciaMax){
                ganador = vehiculo
                distanciaMax = vehiculo.kilometrosActuales
            }
        }

        if (ganador != null) println("El ganador es ${ganador.nombre}") else println("No hay ganador")
    }


    /**
     * Devuelve una clasificación final de los vehículos, cada elemento tendrá el nombre del vehiculo, posición ocupada, la distancia total recorrida, el número de paradas para repostar y el historial de acciones. La collección estará ordenada por la posición ocupada.
     */
    fun obtenerResultados(): List<ResultadoCarrera>{
        val resultados = mutableListOf<ResultadoCarrera>()
        val participantesOrdenados = participantes.sortedByDescending { it.kilometrosActuales }

        for ((index, vehiculo) in participantesOrdenados.withIndex()) {
            val paradasRepostaje = historialAcciones[vehiculo.nombre]?.count { it.contains("repostado") } ?: 0
            val historialAcc = historialAcciones[vehiculo.nombre] ?: listOf()

            resultados.add(ResultadoCarrera(vehiculo, index + 1, vehiculo.kilometrosActuales, paradasRepostaje, historialAcc))
        }

        return resultados

    }



    /**
     * Representa el resultado final de un vehículo en la carrera, incluyendo su posición final, el kilometraje total recorrido,
     * el número de paradas para repostar, y un historial detallado de todas las acciones realizadas durante la carrera.
     *
     * @property vehiculo El [Vehiculo] al que pertenece este resultado.
     * @property posicion La posición final del vehículo en la carrera, donde una posición menor indica un mejor rendimiento.
     * @property kilometraje El total de kilómetros recorridos por el vehículo durante la carrera.
     * @property paradasRepostaje El número de veces que el vehículo tuvo que repostar combustible durante la carrera.
     * @property historialAcciones Una lista de cadenas que describen las acciones realizadas por el vehículo a lo largo de la carrera, proporcionando un registro detallado de su rendimiento y estrategias.
     */
    data class ResultadoCarrera(
        val vehiculo: Vehiculo,
        val posicion: Int,
        val kilometraje: Float,
        val paradasRepostaje: Int,
        val historialAcciones: List<String>
    )
}