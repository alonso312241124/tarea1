¿Qué sucede si intentas borrar una encuesta que no existe? ¿Cómo lo has controlado?
- 
Si intentas eliminar una encuesta, por ejemplo http://localhost:8080/encuesta/del/2 (no existe si tenemos solo una encueta) lo que hará será devolvernos otra vez a /encuestas/admin y no hará nada, ya que, el método delete está diseñado para hacer eso 

Si introduces en un texto del tipo <style>body background-color:red</style> en uno de los campos ¿Qué sucede al ver la encuesta? ¿el navegador ejecuta ese código o no? ¿porqué? ¿cómo podrías hacer que se ejecute ese código o que se deje de ejecutar?
-
El navegador no ejecuta ese código porque usamos Thymeleaf. Al usar la sintaxis th:text, el contenido se muestra como texto plano. Si queremos que ese código se ejecute, podemos usar th:utext que permite que el HTML se interprete y ejecute.

¿Qué has tenido que modificar en el repositorio para filtrar por motivo de búsqueda? ¿has tenido que escribir el código específico o como lo has realizado?
-
He tenido que crear un método en el repositorio, el cual devuelve una lista si le envías un String, en este caso el String es el nivel de satisfacción, este método se usa en el controlador para que dentro del repositorio te encuentre las encuestas con el nivel de satisfacción que le has especificado por parámetro
