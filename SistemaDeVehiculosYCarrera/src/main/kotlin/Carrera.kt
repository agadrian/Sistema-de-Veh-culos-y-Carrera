import kotlin.random.Random

class Carrera (val nombreCarrera: String,
               val distanciaTotal: Float,
               val participantes: List<Vehiculo>,
               var estadoCarrera: Boolean,
               val historialAcciones: MutableMap<String, MutableList<String>>,
               val posiciones: MutableMap<String, Int>) {


    /**
     * Inicia la carrera, estableciendo estadoCarrera a true y comenzando el ciclo de iteraciones donde los vehículos avanzan y realizan acciones.
     */
    fun iniciarCarrera(){
        estadoCarrera = true

        while (estadoCarrera){

        }

    }


    /**
     * Identificado el vehículo, le hace avanzar una distancia aleatoria entre 10 y 200 km. Si el vehículo necesita repostar, se llama al método repostarVehiculo() antes de que pueda continuar. Este método llama a realizar filigranas.
     */
    fun avanzarVehiculo(vehiculo: Vehiculo){
        val distanciaARecorrer = ((10..200).random()).toFloat()
        var distanciaRecorrida = 0f

        while (distanciaRecorrida < distanciaARecorrer){
            val autonomia = vehiculo.calcularAutonomia()
            val numRandom = (1..2).random()

            // Comprobamos que los km que lleva el vehiculo sean menor a los que tiene que recorrer para ver si ha terminado la carrera
            if (vehiculo.kilometrosActuales < distanciaTotal) {


                if (autonomia < distanciaARecorrer){
                    vehiculo.realizaViaje(autonomia)

                }









                // Si la distancia es menor a 20km
                if (distanciaARecorrer < 20f) {
                    // Si no tengo autonomia, reposto
                    if (autonomia < distanciaARecorrer) repostarVehiculo(vehiculo, 0f)
                    vehiculo.realizaViaje(distanciaARecorrer)
                    distanciaRecorrida += distanciaARecorrer

                }

                // Si no tengo para hacer 20km, hago los que pueda, los sumo y reposto
                if (autonomia < 20f) {
                    vehiculo.realizaViaje(autonomia)
                    distanciaRecorrida += autonomia
                    repostarVehiculo(vehiculo, 0f)
                } else {
                    vehiculo.realizaViaje(20f)
                    distanciaRecorrida += 20f
                }


                if (distanciaRecorrida % 20f < 0.01f) {
                    if (numRandom == 1) realizarFiligrana(vehiculo) else {
                        realizarFiligrana(vehiculo)
                        realizarFiligrana(vehiculo)
                    }
                }
            } else{
                vehiculo.kilometrosActuales = distanciaTotal
                distanciaRecorrida = distanciaARecorrer
            }
        }
    }



    /**
     * Reposta el vehículo seleccionado, incrementando su combustibleActual y registrando la acción en historialAcciones.
     */
    fun repostarVehiculo(vehiculo: Vehiculo, cantidad: Float){
        vehiculo.repostar(cantidad)
        //TODO REGISTRAR ACCION
    }


    /**
     * Determina aleatoriamente si un vehículo realiza una filigrana (derrape o caballito) y registra la acción.
     */
    fun realizarFiligrana(vehiculo: Vehiculo) {
        if (vehiculo is Automovil) vehiculo.realizarDerrape()
        else if (vehiculo is Motocicleta) vehiculo.realizarCaballito()
        //TODO REGISTRAR ACCION
    }




    /**
     *  Actualiza posiciones con los kilómetros recorridos por cada vehículo después de cada iteración, manteniendo un seguimiento de la competencia.
     */
    fun actualizarPosiciones(){

    }


    /**
     * Revisa posiciones para identificar al vehículo (o vehículos) que haya alcanzado o superado la distanciaTotal, estableciendo el estado de la carrera a finalizado y determinando el ganador.
     */
    fun determinarGanador(){

    }


    /**
     * Devuelve una clasificación final de los vehículos, cada elemento tendrá el nombre del vehiculo, posición ocupada, la distancia total recorrida, el número de paradas para repostar y el historial de acciones. La collección estará ordenada por la posición ocupada.
     */
    fun obtenerResultados(){

    }
}