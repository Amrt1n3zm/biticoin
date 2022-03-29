# biticoin
simple moneda criptografica ScroogeCoin (1), la autoridad central Scrooge recibe transacciones de usuarios.
 Implementará la lógica utilizada por Scrooge para procesar las transacciones y producir el libro mayor. 
Scrooge organiza transacciones en períodos de tiempo o bloques.En cada bloque, Scrooge recibirá una lista de transacciones, validará las transacciones que recibe y publicará una lista de transacciones validadas.

Tenga en cuenta que una transacción puede hacer referencia a otra en el mismo bloque. Además, entre las transacciones recibidas por Scrooge en un solo bloque, si más de una transacción pasa la misma salida Esto,sería un doble gasto y, por tanto, inválido.

Esto significa que las transacciones no pueden ser validadas aisladamente;  Es un problema difícil elegir un subconjunto de transacciones que son válidas en conjunto.
 Se le proporcionará una clase Transaction que representa una transacción ScroogeCoin y que tiene clases internas Transaction.Output y Transaction.Input. 
 
Una salida de transacción consiste en un valor y una clave pública a la que se está pagando. 
Para las claves públicas, utilizamos la clase Java PublicKey incorporada.

Una entrada de transacción consiste en el hash de la transacción que contiene la salida correspondiente,y una firma digital.  
[el índice de esta salida en esa transacción (los índices son simplemente números enteros a partir de 0)]
 

Para que la entrada sea válida, la firma que contiene debe ser una firma válida sobre la transacción actual con la clave pública en la salida asociada. 

Más específicamente, los datos en bruto que se firman se obtiene a partir del método getRawDataToSign (int index).

 Para verificar una firma, se utilizará el método verifySignature () incluido en el archivo proporcionado Crypto.java:

public static boolean verifySignature (PublicKey pubKey, byte [] message, byte [] signature) 

Este método toma una clave pública, un mensaje y Una firma y devuelve true si y sólo si la firma verifica correctamente el mensaje con la clave pública pubKey.

 Tenga en cuenta que sólo se le da código para verificar las firmas, y esto es todo lo que necesitará para esta asignación.
 El cálculo de las firmas se realiza fuera de la clase Transaction por una entidad que conoce las claves privadas apropiadas.
 Una transacción consiste en una lista de entradas, una lista de salidas y un identificador único (ver el método getRawTx ()). 

La clase también contiene métodos
 para agregar y eliminar una entrada,
 agregar una salida,
 calcular los digeridos para firmar
 / hash, 
agregar una firma a una entrada y calcular y almacenar el hash de la transacción una vez que se hayan agregado todas las entradas / salidas / firmas. 





También se le proporcionará una clase UTXO que representa una salida de transacción no utilizada o no gastada.
 Un UTXO contiene el hash de la transacción desde la que se origina, así como su índice dentro de esa transacción. 
Hemos incluido iguales, hashCode, y compareTo funciones en UTXO que permiten la prueba de igualdad y la comparación entre dos UTXOs sobre la base de sus índices y el contenido de sus arrays txHash.





 Además, se le proporcionará una clase UTXOPool que representa el conjunto actual de UTXOs pendientes y contiene un mapa de cada UTXO a su salida de transacción correspondiente.
 Esta clase contiene constructores para crear un UTXOPool nuevo vacío o una copia de un UTXOPool dado,
 y los métodos para agregar y quitar UTXOs del grupo,
 obtener la salida correspondiente a un UTXO dado, 
comprobar si un UTXO está en el grupo y 
obtener un Lista de todos los UTXO en la agrupación.
 Usted será responsable de crear un archivo llamado TxHandler.java que implemente la siguiente API:


Su implementación de handleTxs () debe devolver un conjunto de transacciones mutuamente válidas de tamaño máximo
(Uno que no puede ser ampliado simplemente agregando más transacciones). No es necesario calcular un conjunto de
Tamaño máximo (uno para el cual no hay un conjunto de transacciones mutuamente válido mayor).
Sobre la base de las transacciones que ha elegido aceptar, handleTxs también debe actualizar su interno
UTXOPool para reflejar el conjunto actual de salidas de transacciones no utilizadas, de modo que las
HandleTxs () y isValidTx () pueden procesar / validar correctamente las transacciones que reclaman
Salidas de transacciones que fueron aceptadas en una llamada anterior a handleTxs ().




Crédito adicional: Cree un segundo archivo llamado MaxFeeTxHandler.java cuyo método handleTxs ()
Encuentra un conjunto de transacciones con los honorarios totales máximos de transacción - es decir, maximizar la suma de todos
Transacciones en el conjunto de (suma de valores de entrada - suma de valores de salida)).



In ScroogeCoin (Lecture 1), the central authority Scrooge receives transactions from users. You will implement the logic used by Scrooge to process transactions and produce the ledger. Scrooge organizes transactions into time periods or blocks. In each block, Scrooge will receive a list of transactions, validate the transactions he receives, and publish a list of validated transactions.
Note that a transaction can reference another in the same block. Also, among the transactions received by Scrooge in a single block, more than one transaction may spend the same output. This would of course be a double-spend, and hence invalid. This means that transactions can’t be validated in isolation; it is a tricky problem to choose a subset of transactions that are together valid.
You will be provided with a Transaction class that represents a ScroogeCoin transaction and has inner classes Transaction.Output and Transaction.Input.
A transaction output consists of a value and a public key to which it is being paid. For the public keys, we use the built-in Java PublicKey class.
A transaction input consists of the hash of the transaction that contains the corresponding output, the index of this output in that transaction (indices are simply integers starting from 0), and a digital signature. For the input to be valid, the signature it contains must be a valid signature over the current transaction with the public key in the spent output. 
More specifically, the raw data that is signed is obtained from the getRawDataToSign(int index) method. 

To verify a signature, you will use the verifySignature() method included in the provided file Crypto.java:
public static boolean verifySignature(PublicKey pubKey, byte[] message, byte[] signature) 
This method takes a public key, a message and a signature, and returns true if and only signature correctly verifies over message with the public key pubKey.


Note that you are only given code to verify signatures, and this is all that you will need for this assignment. The computation of signatures is done outside the Transaction class by an entity that knows the appropriate private keys.
A transaction consists of a list of inputs, a list of outputs and a unique ID (see the getRawTx() method). The class also contains methods to add and remove an input, add an output, compute digests to sign/hash, add a signature to an input, and compute and store the hash of the transaction once all inputs/outputs/signatures have been added.
You will also be provided with a UTXO class that represents an unspent transaction output. A UTXO contains the hash of the transaction from which it originates as well as its index within that transaction. We have included equals, hashCode, and compareTo functions in UTXO that allow the testing of equality and comparison between two UTXOs based on their indices and the contents of their txHash arrays.
Further, you will be provided with a UTXOPool class that represents the current set of outstanding UTXOs and contains a map from each UTXO to its corresponding transaction output. This class contains constructors to create a new empty UTXOPool or a copy of a given UTXOPool, and methods to add and remove UTXOs from the pool, get the output corresponding to a given UTXO, check if a UTXO is in the pool, and get a list of all UTXOs in the pool. 
You will be responsible for creating a file called TxHandler.java that implements the following API:

Public class TxHandler {







  /** Creates a public ledger whose current UTXOPool 



    * (collection of unspent transaction outputs) is 

        utxoPool. 



    * This should  make a defensive copy of utxoPool by 

        using 



    * the UTXOPool (UTXOPool uPool) constructor.



    */



  public TxHandler(UTXOPool utxoPool);







  /** Returns true if



   * (1) all outputs claimed by tx are in the current 

       UTXO pool



   * (2) the signatures on each input of tx are valid



   * (3) no UTXO is claimed multiple times by tx



   * (4) all of tx’s output values are non-negative



   * (5) the sum of tx’s input values is greater than 

       or equal 



   * to the sum of its output values; and false 

       otherwise.



   */



  public boolean isValidTx(Transaction tx);







  /** Handles each epoch by receiving an unordered 

      array of 



   * proposed transactions, checking each transaction 

       for 



   * correctness, returning a mutually valid array of 

       accepted 



   * transactions, and updating the current UTXO pool 

       as 



   * appropriate.



   */



  public Transaction[] handleTxs(Transaction[] 

      possibleTxs);







}


your implementation of handleTxs() should return a mutually valid transaction set of maximal size (one that can’t be enlarged simply by adding more transactions). It need not compute a set of maximum size (one for which there is no larger mutually valid transaction set).
Based on the transactions it has chosen to accept, handleTxs() should also update its internal UTXOPool to reflect the current set of unspent transaction outputs, so that future calls to handleTxs() and isValidTx() are able to correctly process/validate transactions that claim outputs from transactions that were accepted in a previous call to handleTxs().


Extra Credit: Create a second file called MaxFeeTxHandler.java whose handleTxs() method finds a set of transactions with maximum total transaction fees -- i.e. maximize the sum over all transactions in the set of (sum of input values - sum of output values)).





En el segmento 1.1 vamos a hablar de funciones de hash criptográficas. Vamos a hablar de lo que son, y cuáles son sus propiedades. Y luego más adelante vamos a seguir adelante y hablar sobre cuáles son sus aplicaciones. Por lo tanto, una función de hash criptográfica es una función matemática. Y tiene tres atributos que tenemos que empezar. En primer lugar, una función hash puede tomar cualquier cadena como entrada, absolutamente cualquier cadena de cualquier tamaño. Produce una salida de tamaño fijo, usaremos 256 bits en esta serie de conferencias, porque eso es lo que hace bitcoin. Y tiene que ser eficientemente computable, es decir, dado una cadena, en un período de tiempo razonable, puede averiguar cuál es la salida. Así que esa es una función hash, pero vamos a necesitar funciones hash que sean criptográficamente seguras. Las propiedades criptográficas de las funciones hash son un tema complicado en general. Pero nos centraremos aquí en tres propiedades particulares. Y explicaré en un minuto cuáles son.
0:53
En particular, que la función es libre de colisiones, que tiene una propiedad oculta, y que es amigable con el rompecabezas. Y para cada uno de estos, voy a hablar de lo que la propiedad es, lo que significa. Y luego voy a hablar de por qué es útil tener una función que tiene esa propiedad. Así que, primero, sin colisiones. Por lo tanto, la primera propiedad que necesitamos de una función de hash criptográfica es que es libre de colisión. Y lo que esto significa es que es imposible, nadie puede encontrar los valores xey, de modo que xey son diferentes, y sin embargo el hash de x es igual al hash de y.
1:25
Por lo tanto, si observamos el funcionamiento de la función representada por una de estas flechas rojas. Aquí tenemos x y H (x), y aquí tenemos y y H (y). Entonces nadie puede encontrar una situación como esta. Que usted tiene una xey que están separados, y sin embargo, cuando hash ellos, hash al mismo valor. Ahora una cosa a notar es que dije, nadie puede encontrar. No he dicho que no hay colisión, porque si piensas en ello tiene que haber una colisión. Las colisiones existen, y para entender por qué es así, podemos usar este diagrama. Aquí a la izquierda, estoy representando todas las posibles entradas a esta función, que puede ser una cadena de cualquier tamaño. Y aquí, tengo todas las salidas posibles, que tiene que ser cadena de 256 bits de tamaño. Así que el lado derecho aquí, las salidas, sólo hay 2 a las 256 posibilidades. Aquí hay más posibilidades. Y así, si piensas que cada punto aquí a la izquierda va a ser mapeado por una flecha, a algún punto a la derecha. Usted puede ver que a medida que vaya desde todos los puntos de aquí a la izquierda en la derecha, tiene que estar lleno de gente. Y de hecho, que tiene que haber varios valores aquí a la izquierda que el mapa a la misma salida aquí. De hecho, en general, habrá un gran número de entradas posibles que se asignarán a cualquier salida en particular. Así que existen colisiones. Dije antes que nadie pueda encontrar una colisión. Y esa es la pregunta clave. Sabemos que existen colisiones. La pregunta es si hay alguna colisión que se pueden encontrar por personas regulares que usan computadoras normales?
2:50
Bueno, ahora para empeorar las cosas, dije que tiene que ser imposible encontrar una colisión. Déjeme decirle cómo encontrar una colisión, porque hay un método que está garantizado para trabajar. Y el método funciona así. Que vamos a elegir 2 a las 130 entradas elegidas al azar, sobre la nube izquierda de ese diagrama anterior. Y si seleccionamos esas 2 a las 130 entradas elegidas aleatoriamente, resulta que hay un 99.8% de probabilidad de que al menos dos de ellas se colisionen. Y este es un método sencillo para encontrar una colisión. Funciona sin importar cuál sea la función hash, pero por supuesto, el problema es que esto toma un tiempo muy, muy largo de hacer. Tienes que calcular la función hash 2 a las 130 veces. Y eso es, por supuesto, un número astronómico. Este método funciona sin importar la función de hash que estamos utilizando. Todavía hay un 99.8% de probabilidad de que esto funcione. Y si no funciona, sólo intentarlo de nuevo, probablemente funcionará la próxima vez. Pero, esto realmente no importa. Y la razón de que realmente no importa, es que este procedimiento toma 2 a los 130 pasos, para llegar a esa alta probabilidad. Así, podemos decir algo como esto. Podemos decir que si cada computadora hecha por la humanidad estaba computando desde el principio de todo el universo hasta ahora, las probabilidades de que hubieran encontrado una colisión son todavía infinitesimalmente pequeñas. Tan pequeño que es mucho menos que la probabilidad de que la Tierra sea destruida por un meteorito gigante en los próximos dos segundos, lo que no ocurrió.
4:14
Bien, así que sabemos cómo encontrar una colisión. Pero este método tarda demasiado en importar. La pregunta es, ¿hay algún otro método que se podría utilizar en una función hash particular, con el fin de encontrar una colisión? Y esa es la pregunta que es más difícil de responder. ¿Hay una manera más rápida de encontrar colisiones? Bueno, para algunos posibles valores de las funciones hash, por supuesto que sí. Por ejemplo, si nuestra función hash simplemente tomar la entrada, módulo 2 a la 256, es decir, acaba de seleccionar los últimos 256 bits de la entrada. Entonces conoceríamos una manera fácil de encontrar una colisión. Una colisión sería los valores 3 y 3 más 2 a los 256. Por lo tanto, para algunos posibles valores de la función hash, es muy fácil encontrar una colisión. Para otros, no lo sabemos.
5:01
Ahora, una cosa que necesito anotar es que no hay ninguna función hash que se ha demostrado ser libre de colisión. Hay sólo algunos que la gente ha intentado realmente, realmente difícil de encontrar colisiones y no han tenido éxito. Y por eso elegimos creer que no tienen colisión. Bien, ahora, ¿de qué sirve la libertad de colisión? Si podemos asumir que tenemos una función hash que es libre de colisión, entonces podemos usar esa función de hash como mensaje digerir. Y lo que quiero decir con eso es lo siguiente. Que si sabemos que xey tienen el mismo hash, entonces es seguro asumir que xey son los mismos. Porque si alguien sabía que una xyy que eran diferentes, que tenía el mismo hash, por supuesto, que sería una colisión. Puesto que no hay una colisión que sepamos de, después saber los hashes son iguales, podemos asumir que los valores son iguales. Y esto nos permite usar el hash como una especie de resumen de mensajes. Supongamos, por ejemplo, que teníamos un archivo, un archivo muy grande. Y queríamos ser capaces de reconocer después si otro archivo era el mismo que el archivo que vimos la primera vez, ¿verdad? Así que una manera de hacerlo sería guardar todo el archivo grande. Y luego cuando vimos otro archivo más tarde, sólo compararlos. Pero debido a que tenemos hashes que creemos que son libres de colisiones, es más eficiente sólo recordar el hash del archivo original. Entonces, si alguien nos muestra un nuevo archivo y afirma que es el mismo, podemos calcular el hash de ese nuevo archivo y comparar los hashes. Si los hashes son los mismos, entonces concluimos que los archivos deben haber sido los mismos. Y eso nos da una manera muy eficiente de recordar cosas que hemos visto antes y reconocerlas de nuevo. Y, por supuesto, esto es útil porque el hash es pequeño, es sólo 256 bits, mientras que el archivo original puede ser muy grande. Así que hash es útil como un resumen de mensajes. Y veremos, más adelante en esta conferencia, y en conferencias posteriores, por qué es útil usar el hash como un resumen de mensaje.
6:49
Por lo tanto, la segunda propiedad que queremos de nuestra función hash es que se está escondiendo. Y la propiedad que queremos es algo como esto. Que si se nos da la salida de la función hash, que no hay forma factible de averiguar cuál fue la entrada x. El problema es que esta propiedad no se sostiene exactamente. Y para entender por qué ese es el caso, veamos este ejemplo.
7:10
Así que aquí, lo que vamos a hacer es un experimento donde volteamos una moneda. Y si el resultado de la moneda flip era cabezas, vamos a devolver el hash de la cadena "cabezas". Y si el resultado fue cola, vamos a devolver el hash de la cadena "colas".
7:24
Y ahora vamos a preguntar a alguien que no vio la moneda voltear, pero sólo vio esta salida de hash, para averiguar lo que la cadena fue que fue hash. Eso, por supuesto, va a ser fácil. En este escenario es fácil encontrar la cadena de entrada, es fácil encontrar x. Usted simplemente calcular el hash de la cadena "cabezas" y el hash de la cadena "colas", y verá cuál usted consiguió.
7:45
Y así, en sólo un par de pasos, usted puede averiguar qué x era. Así que la razón de este ejemplo fracasó, que es la razón por la que un adversario fue capaz de adivinar lo que la cadena era, era que había sólo un par de posibles valores de x.
8:01
Y así, si vamos a tener una propiedad oculta como esta, tiene que ser el caso de que no hay valor de x que es particularmente probable. Es decir, x tiene que ser elegido de un conjunto que es, en cierto sentido, muy disperso. De modo que este método para el adversario de probar todos los valores posibles de x, o simplemente probar unos pocos valores de x que son especialmente probables, no va a funcionar. Así que la propiedad escondida que vamos a tener que configurar es un poco más complicada. Y la forma en que vamos a arreglar este problema con el valor común x, como cabezas y colas, es que vamos a tomar la x. Y vamos a poner al lado de él, vamos a concatenar con él, un valor, r, que se elige de una distribución que es realmente extendido.
8:43
Y así este H (r | x), que significa tomar todos los bits de r, y poner después de ellos todos los bits de x. Y así lo que vamos a decir se da el hash de r junto con x, que es imposible encontrar x. Y que esto será cierto en la propiedad formalmente declarada que, si r es un valor aleatorio elegido de una distribución que tiene alta min-entropía, entonces, dado H (r | x), es inviable encontrar x. ¿Y qué significa alta min-entropía? Bueno, captura esta idea intuitiva de que r se elige de una distribución que está realmente extendida. Y lo que esto significa específicamente es que no hay un valor particular que r pudiera haber tenido, que ocurriría con más de una probabilidad insignificante. Así, por ejemplo, si r se elige de manera uniforme entre todas las cadenas de 256 bits de largo, entonces cualquier cadena particular se eligió con probabilidad 1 en 2 a la 256, que es realmente un valor insignificante. Por lo tanto, siempre y cuando r fue elegido de esa manera, entonces el hash de r concatenado con x va a ocultar x. Y esa es la propiedad oculta que se considerará que tiene la función hash. Bueno, ahora veamos una aplicación de esa propiedad oculta. Y, en particular, lo que queremos hacer es algo llamado un compromiso. Y esto es algo así como la analogía digital de tomar un valor, un número, y sellarlo en un sobre, y poner ese sobre en la mesa, donde todos pueden verlo. Ahora, cuando haces eso, te has comprometido con lo que hay en el sobre.
10:09
Pero no lo has abierto, es secreto de todos los demás. Más tarde, puede abrir el sobre y sacar el valor, pero está cerrado. Así que comprometerse con un valor y revelarlo más tarde. Queremos hacer eso en un sentido digital. Por lo tanto, para ser más específico sobre lo que es la API que vamos a proporcionar aquí, la API de compromiso se ve así, que hay dos cosas que puede hacer. En primer lugar, puede comprometerse con un mensaje. Y eso va a devolver dos valores, un compromiso y una clave. Piense en el compromiso como el sobre que va a poner en la mesa, y la clave como clave secreta para desbloquear el sobre. Luego, usted permite que alguien más lo verifique, dado el compromiso y una clave, que usted les ha dicho mientras tanto, y el mensaje. Para que puedan verificar que ese compromiso, clave y mensaje realmente van juntos. Y esto devolverá un verdadero o falso. Bueno, ahora para sellar un msg en un sobre, lo que hacemos es nos comprometemos con el mensaje. Y eso devuelve un compromiso y una clave, y luego publicamos el compromiso. Eso es poner el sobre en la mesa. Ahora, más tarde, para abrir el sobre, lo que vamos a hacer es publicar la clave y el mensaje que nos comprometimos a. Y entonces cualquiera puede usar esta llamada de verificación, con el compromiso que hemos publicado anteriormente, la clave y el mensaje que acabamos de anunciar, para comprobar la validez de nuestra apertura del sobre. Bueno, y la propiedad, por supuesto, queremos de esto, es que se comporta como un sello de un sobre. Y, en particular, las dos propiedades de seguridad son estas. En primer lugar, dado com, el compromiso, el sobre en la mesa, que alguien mirando el sobre no puede averiguar cuál es el mensaje.
11:39
La segunda propiedad es que es vinculante, que cuando te comprometas con lo que hay en el sobre, no puedes cambiar de opinión más tarde. Es decir, es imposible encontrar dos mensajes diferentes, de tal manera que pueda comprometerse con un mensaje, y luego afirmar que se comprometió con otro, y todo se verificará.
11:54
Bueno, ¿cómo sabemos que estas dos propiedades tienen? Bueno, primero tenemos que hablar de cómo vamos a implementar los compromisos. Y la forma en que vamos a implementar los compromisos es así.
12:03
Que con el fin de comprometerse con un mensaje de valor, 
vamos a generar un valor aleatorio de 256 bits y lo llamamos la clave.
  como el compromiso, devolver el hash de la clave concatenada junto con el mensaje. 
Y como valor clave, vamos a devolver H de esta clave. 

Y luego, para verificar, alguien va a calcular este mismo hash
 de la clave que se les dio, concatenado con el mensaje. 

Y van a comprobar si eso es igual al compromiso que vieron,

 ¿de acuerdo? Así que esta es una manera de usar las funciones hash tanto en el compromiso como en la verificación. Así que ahora las propiedades de seguridad. Si bajamos a las propiedades de seguridad que estaban en la parte inferior de la diapositiva anterior, y simplemente incluimos las definiciones de cómo vamos a implementar esto aquí.
 Es decir, esto se utiliza para decir com, dado com infeasible para encontrar msg, que acaba de conectar lo que es COM. Com es el hash de la clave concatenada con msg.

 Y de manera similar aquí abajo, esto es lo que sucede cuando tomamos lo que estaba escrito antes y conectamos la definición de verify in com. Bueno, ahora lo que estas propiedades se convierten, 
la primera se da H (key | msg), es imposible encontrar msg. Bueno, resulta que esa es exactamente la propiedad escondida de la que hablamos antes.
 La clave se eligió valor aleatorio de 256 bits. Y por lo tanto, la propiedad oculta dice que si tomamos el mensaje, y ponemos delante de él algo que fue elegido de una distribución muy extendida, como dije un valor aleatorio de 256 bits, entonces es imposible encontrar el mensaje.

 Así que esto es exactamente la propiedad escondida. Y este aquí abajo resulta ser exactamente la propiedad sin colisiones. De modo que si alguien puede encontrar dos mensajes que tienen el mismo hash como este, entonces tienen un valor de entrada aquí y un valor de entrada que son diferentes, y sin embargo tienen el mismo hash. Y por lo tanto, debido a las dos propiedades de seguridad de las que hemos hablado hasta ahora para los hashes, este esquema de compromiso funcionará, en el sentido de que tendrá las propiedades de seguridad necesarias. Bien, así que esa es la segunda propiedad de seguridad de los hashes, que se esconden. Y la aplicación de eso es compromisos. La tercera propiedad de seguridad que vamos a necesitar es que sean amigables con los rompecabezas. Y esto es, una vez más, un poco más complicado, pero permítanme ir a través de él poco a poco.

 Eso para cualquier valor de salida posible y que usted puede ser que desee de la función del hash. Vamos a usar y como un valor de salida del hash más tarde.
 Que si k se elige de una distribución que tiene alta min-entropía. Es decir, k se elige aleatoriamente de algún conjunto que está super disperso. Entonces no hay forma de encontrar una x, tal que el hash de kyx sea igual a y. 

Por lo tanto, lo que esto significa es básicamente que si alguien quiere apuntar a la función hash, si quieren que salga a un determinado valor de salida y. Que si hay parte de la entrada que se elige de una manera adecuada al azar, que es muy difícil encontrar otro valor que golpea exactamente ese objetivo.

 Así que la aplicación que vamos a utilizar de esto, vamos a construir un rompecabezas de búsqueda. Y lo que esto significa es que vamos a construir un problema matemático, que requiere buscar un espacio muy grande para encontrar la solución. Y donde no hay atajos, una forma de encontrar una buena solución, aparte de buscar ese gran espacio. Eso es un rompecabezas de búsqueda.
 Para ser más específico, la idea es que si se nos da una ID de rompecabezas, que se elige de alguna distribución de alta min-entropía. Esa es una distribución de probabilidad muy dispersa. Y se nos da un conjunto de objetivos, Y, que alguien quiere hacer caer la función hash. Entonces queremos tratar de encontrar una solución, x. De modo que si tenemos el identificador del rompecabezas junto con la solución X, obtenemos un resultado que está en el conjunto Y. Así que la idea es Y es un rango objetivo o un conjunto de resultados hash que queremos. ID especifica un rompecabezas en particular, y x es una solución al rompecabezas.
15:57
Y la propiedad rompecabezas aquí implica que no hay una estrategia de solución para este rompecabezas, que es mucho mejor que simplemente intentar valores aleatorios de x. Y así si queremos plantear un rompecabezas que es difícil de resolver, que podemos hacerlo de esta manera, siempre y cuando se puede generar ID de rompecabezas de una manera adecuada al azar. 

Y vamos a usar eso más tarde cuando hablamos de minería bitcoin. Ese es el tipo de rompecabezas computacional que vamos a usar.

 Bueno, por lo que hemos hablado de tres propiedades de las funciones de hash y una aplicación de cada uno de ellos. Ahora permítanme hablar brevemente sobre la función de hash particular que vamos a usar. Hay un montón de funciones hash en existencia, pero este es el bitcoin uno utiliza, y es una muy buena para usar. Se llama SHA-256 o SHA-256, y funciona así. Básicamente, toma el mensaje de que has hash, y lo divide en bloques que tienen 512 bits de tamaño. El mensaje no va a ser, en general, necesariamente un múltiplo del tamaño del bloque, por lo que vamos a añadir un relleno al final. Y el relleno consistirá en, al final del relleno, un campo de longitud de 64 bits, que es la longitud del mensaje en bits. Y luego antes de eso, va a consistir en un bit, seguido por un número de bits cero. Y escoge el número de bits cero para que esto salga exactamente al final de un bloque. Así que una vez que haya rellenado el mensaje para que su longitud sea exactamente un múltiplo del tamaño del bloque de 512 bits, entonces lo corta en bloques y, a continuación, ejecuta este cálculo. Comienzas con el valor de 256 bits llamado IV. Eso es sólo un número que buscas en un documento de normas. Y luego tomar la IV y el primer bloque del mensaje. Usted toma esos 768 bits totales, y los ejecuta a través de esta función especial, c, la función de compresión, y sale 256 bits. Usted ahora toma eso con los 512 bits siguientes del mensaje, lo corre a través de c otra vez, y usted continúa. Cada iteración de c crunches en otro bloque de 512 bits del mensaje y lo mezcla, de manera lógica al resultado. Y cuando llegas al final, has consumido todos los bloques del mensaje más el relleno. El resultado es el hash, que es un valor de 256 bits. Y es fácil demostrar que, si esta función, c, esta función de compresión está libre de colisión, entonces toda esta función hash también estará libre de colisiones. Las otras propiedades son un poco más complicadas, así que no voy a hablar de ellos aquí.
18:15
De acuerdo, hemos hablado de funciones hash. Hemos hablado de lo que hacen las funciones de hash. Hemos hablado de tres propiedades de las funciones de hash y las aplicaciones de esas propiedades, y la función de hash específico que utilizamos en bitcoin. En el siguiente segmento de conferencia, hablaremos sobre las formas de usar las funciones hash para construir estructuras de datos más complicadas que se usan en sistemas distribuidos como bitcoin


En la sección 1.2, vamos a hablar sobre los punteros de Hash y su aplicación. Un puntero hash es un tipo de estructura de datos que resulta ser utilizado mucho en los sistemas de los que estamos hablando. Y un puntero hash es básicamente una cosa simple, que vamos a tomar un puntero a donde se almacena alguna información. Y vamos a ir junto con el puntero almacenar un hash criptográfico de la información. Así que mientras que un puntero regular le da una manera de recuperar la información. Un puntero hash nos va a dejar pedir que nos devuelvan la información.
0: 33 También nos va a permitir verificar que la información no ha cambiado. Así que un puntero hash nos dice dónde está algo y cuál es su valor.
Y vamos a dibujar un puntero hash en diagramas como este. Que vamos a tener cada uno, y luego una flecha que apunta a algo. Así que cualquier cosa dibujada de esta manera, piensa que es un puntero hash a esta cosa. Es un puntero a donde se almacena y es también el hash del valor que estos datos tuvieron cuando la vimos por última vez.
01:01 Y podemos tomar los punteros hash y podemos usarlos para construir todo tipo de estructuras de datos. Así que una idea clave aquí, tomar cualquier estructura de datos o listas de enlaces o árbol de búsqueda binaria o algo así y ponerlo en práctica con punteros de hash en lugar de punteros como lo haríamos normalmente.
1: 17Por ejemplo aquí hay una lista enlazada que construimos con punteros de hash. Y esta es una estructura de datos que vamos a llamar una cadena de bloque.
1: 25 Así como una lista vinculada regular donde tiene una serie de bloques y cada bloque tiene datos, así como un puntero al bloque anterior en la lista, aquí el puntero de bloque anterior será reemplazado por un puntero hash. Así que dice dónde está y cuál fue el valor de todo este bloque anterior. Y vamos a almacenar, vamos a recordar al jefe de la lista como este. Al igual que un puntero de hash regular. Y un caso de uso de esto para un tren de bloque como este es un registro de manipulación indebida, es decir, si queremos construir una estructura de datos de registro que almacena un montón de datos. Para que podamos agregar datos al final del registro, pero si alguien va más tarde y se ensucia con los datos que están antes en el registro vamos a ser detectarlo. Eso es lo que significa evidencia de temperamento. Así que para entender por qué una cadena de bloque nos da esta propiedad de manipulación indebida. Vamos a preguntar qué sucede si un adversario quiere volver y manipular los datos más tarde que está en el centro de la cadena. Asumamos que un adversario quiere manipular este bloque aquí. Quiere cambiar los datos aquí. Y él quiere hacerlo de tal manera que nosotros, los titulares del puntero hash en la cabeza aquí, no podremos detectarlo.
2: 34 Así que el adversario cambió el contenido de este bloque. Y por lo tanto, el hash aquí que es un hash de todo este bloque no va a mash up porque la función hash es libre de colisión, debe ser el caso de que el hash de este bloque es ahora diferente. Y así podríamos detectar la inconsistencia entre estos datos y el puntero hash que recordamos antes o podríamos hacer eso a menos que la advertencia permita que se altere con el puntero hash. Si se altera con este puntero hash hace que estos dos coinciden. Pero ahora ha cambiado el contenido de este bloque. Y lo que eso significa es que cuando volvamos más tarde y hash el contenido de este bloque, no va a coincidir con el hash que recordamos antes porque el contenido del bloque ha cambiado. Y así vamos a detectar la inconsistencia entre el contenido de este bloque y este hash, a menos que el adversario también altere con el bloque aquí a la derecha. Pero ahora, cuando lo hace, el hash de este bloque no va a coincidir con el hash que recordamos aquí y el hash que nos aferramos a. Y esto el adversario no puede manipular porque este es el valor que recordamos como el jefe de la lista. Y así, el resultado de esto es que si el adversario quiere manipular datos en cualquier parte de toda esta cadena, para mantener la historia coherente, tendrá que manipular los punteros de hash hasta el principio. Y en última instancia, se va a ejecutar en un bloque de carretera porque no será capaz de manipular el jefe de la lista. Y lo que esto significa es que con sólo recordar este puntero hash, hemos recordado esencialmente un tipo de hash, un hash de manipulación indebida de toda la lista hasta el principio. Y así podemos construir una cadena de bloque como ésta que contenga tantos bloques como queramos volver a algún bloque especial al principio de la lista que podríamos llamar el bloque de genesis. Y eso es un registro de evidencia de manipulación construido fuera de la cámara del bloque.
4: 22 Ahora, otra estructura de datos útil que podemos construir usando punteros de hash es un árbol binario. Podemos construir un árbol binario con punteros de hash y esto se llama en la jerga, un árbol de Merkle después de Ralph Merkle que lo inventó. Y la idea es esta, supongamos que tenemos un montón de bloques de datos que vamos a dibujar a través de la parte inferior aquí. Vamos a tomar pares consecutivos de estos bloques de datos y para estos dos bloques de datos vamos a construir una estructura de datos aquí que tiene dos punteros de hash, uno a cada uno de estos bloques, y de manera similar todo el camino. A continuación, vamos a otro nivel y este bloque aquí contendrá un puntero hash de estos dos niños aquí. Y así sucesivamente, todo el camino de regreso hasta la raíz del árbol. Y luego, como antes, vamos a recordar sólo el puntero hash aquí en la cabeza del árbol. Y podemos entonces, si queremos recorrer a través de los punteros de hash a cualquier punto de la lista. Y podemos asegurarnos de que los datos no han sido manipulados. Porque al igual que te mostré con la cadena de bloque, si un adversario teta con algún bloque aquí abajo en la parte inferior con los datos que harán que el puntero hash que es un nivel hasta no coinciden. Así que tendrá que manipular eso. Y por lo tanto, tendrá que manipular el puntero hash un nivel desde allí. Y eventualmente llegará a la cima, donde no será capaz de alterar el puntero hash que hemos recordado. Por lo tanto, de nuevo, cualquier intento de alterar cualquier pieza de datos a través de la parte inferior será en contra de corto, con sólo recordar el puntero hash en la parte superior.
5: 50 Ahora, otra característica interesante de los árboles Merkle, es que a diferencia de la cadena de bloques que construimos antes, que si alguien quiere demostrarnos que un bloque de datos en particular es un miembro de este árbol Merkle. Todo lo que necesitan para mostrarnos es esta cantidad de datos. Así que si recordamos sólo la raíz y alguien quiere convencernos de que este bloque está en el árbol de Merkle, necesitan mostrarnos este bloque. Y podemos comprobar que el hash coincide. Y luego tienen que mostrarnos este bloque y podemos verificar que el hash de esto coincide con eso. Ellos pueden mostrarnos este bloque. Verificamos que el hash de este bloque coincida con este puntero hash. Y luego nos muestran los datos. Y sólo por verificar los hashes hasta la raíz, podemos asegurar, podemos verificar que este bloque de datos estaba en el árbol de Merkle. Así que se tarda unos log n elementos que tenemos que mostrar, y se tarda alrededor de log n tiempo para que lo verifiquemos. Y así en el número muy grande de bloques de datos en el árbol de Merkle, todavía podemos verificar la calidad de miembro probada en un tiempo relativamente corto.
6: 54 Así que los árboles Merkle tienen varias ventajas. Una ventaja, por supuesto, es que el árbol tiene muchos elementos, pero sólo tenemos que recordar el hash de una raíz que es de sólo 256 bits. Podemos verificar la pertenencia a un árbol Merkle en tiempo logarítmico y espacio logarítmico. Eso es bueno. Y hay una variante que es un árbol Merkle ordenado. Eso es solo un árbol Merkle donde tomamos los bloques en la parte inferior y los clasificamos en algún orden. Diga el orden alfabético, lexicográfico o numérico o algún orden en el que estamos de acuerdo. Una vez hecho eso, una vez que hemos ordenado el árbol Merkle ahora, es posible verificar la no pertenencia a un árbol Merkle. Es decir, podemos probar que un bloque en particular no está en el árbol de Merkle. Y la forma en que lo hacemos es simplemente mostrando un camino al elemento que es justo antes de donde ese elemento sería y justo después de donde sería. Y entonces podemos decir mirar, ambos elementos están en el árbol Merkle, son consecutivos. Y por lo tanto no hay espacio entre ellos. No hay nada entre ellos y por lo que la cosa que estamos tratando de demostrar la no pertenencia de no puede estar allí. El árbol de Merkle es árbol binario de la búsqueda, construido con los punteros del hash, podemos hacer pruebas de la calidad del tiempo logarítmicas, pruebas de la no-pertenencia si clasificamos el árbol y es muy eficiente.
Más en general, resulta que podemos usar tiene punteros en cualquier estructura de datos basada en puntero, siempre y cuando la estructura de datos no tenga ciclos. Si hay ciclos en la estructura de datos, entonces no podremos hacer coincidir todos los hashes. Si lo piensas en una estructura de datos acíclica, podemos empezar de cerca de las heces o cerca de las cosas que no tienen punteros que salgan de ellos, calcula los hashes de esos y luego trabajamos nuestro camino de regreso, comenzando. Pero en una estructura con ciclos, no hay fin que podamos comenzar y calcular de regreso. Así por ejemplo, un gráfico acíclico dirigido, a partir de punteros hash y podremos verificar la membresía en ese día de manera muy eficiente. Y será fácil de calcular. Así que este es un truco general que verá una y otra vez a través de las estructuras de datos distribuidos ya través de los algoritmos de los que hablaremos más adelante en esta conferencia y en conferencias posteriores.


En el segmento 1.3, vamos a hablar de firmas digitales. Esta es la segunda primitiva criptográfica junto con las funciones de hash que necesitamos como bloques de construcción para la discusión de la criptocurrencia más adelante. Así que una firma digital se supone que es como una firma en papel sólo en forma digital. Y lo que esto significa es que lo que queremos de las firmas es dos cosas. En primer lugar, que al igual que una firma de papel ideal, sólo usted puede hacer su firma, pero cualquiera que vea su firma puede verificar que es válido. Y entonces la segunda cosa que usted quiere es que la firma se ata a un documento particular. Así que alguien no puede tomar su firma y recortar un documento y pegarlo en el fondo de otro porque la firma no es sólo una firma. Significa su acuerdo o aprobación de un documento en particular. Bien, entonces la pregunta es ¿cómo podemos construir esto en una forma digital usando criptografía?
1:03 Así que vamos a entrar en las tuercas y los pernos. Aquí hay una API para firmas digitales.
1: 07 Hay tres cosas, tres operaciones que necesitamos ser capaces de hacer. La primera es que necesitamos, al principio, ser capaces de generar claves, y así tenemos una operación generateKeys. Y le decimos un tamaño de clave, ¿cuán grande en bits deberían ser las claves? Y esto produjo dos claves, sk y pk. Sk será una clave de firma secreta, esta es la información que mantenga en secreto que utiliza para hacer su firma. Y pk es una clave de verificación pública que vas a dar a todo el mundo y que cualquiera puede usar para verificar tu firma cuando la vean.
1: 39 La segunda operación es la operación de signo. La operación de signo, toma su clave de firma secreta y toma un mensaje que quiere poner su firma. Y vuelve, sig que es una firma. Es sólo una cadena de bits que representan su firma. Y luego, la tercera operación es una verificación, que toma algo que pretende ser una firma válida y verifica que es correcta. Toma la clave pública del firmante, toma el mensaje de que la firma está supuestamente encendida y toma la supuesta firma. Y simplemente dice sí o no, ¿es esto una firma válida?
2: 14Okay, por lo que estas tres operaciones, estos tres algoritmos constituyen un esquema de firma. Y voy a notar que los dos primeros pueden ser algoritmos aleatorios. La verificación no será. Siempre será determinista. Y de hecho si piensas en ello, genereKeys tendría que ser mejor asignado al azar, porque debería estar generando claves diferentes para diferentes personas.
2: 33Okay, por lo que los requisitos para las firmas, a un nivel un poco más técnico, son los dos requisitos siguientes. En primer lugar, que las firmas válidas verifican. Si una firma es válida, es decir, si firmo un mensaje con sk, con mi clave secreta, que si alguien luego intenta validar que usando mi clave pública y el mismo mensaje, eso validará correctamente. Así que esto dice que las firmas son útiles en absoluto. Pero entonces la segunda cosa que usted quiere es que es imposible forjar firmas. Es decir, un adversario que conoce su clave pública, quién conoce su clave de verificación y consigue ver firmas en algunos otros mensajes, no puede forjar su firma en algún mensaje en el que quiera forjarlo.
3: 20Y para explicar esta propiedad con más detalle, se formula normalmente en términos de un juego que jugamos con un adversario. Así que el juego que voy a describir aquí con este diagrama. Así que aquí a la izquierda es el retador que es un juez de televisión y el retador va a probar una reclamación de un atacante. El atacante afirma que puede forjar firmas. Y vamos a probar esa afirmación y el juez emitirá un juicio sobre ella. El atacante aquí, este tipo, es en realidad Whit Diffie, uno de los inventores de las firmas digitales, del concepto de firmas digitales y un criptógrafo distinguido. Así que pensé que le dejaría jugar el rol de atacante aquí. De acuerdo, así que el juego funciona así. Lo primero que hacemos es usar generar claves para generar una clave secreta, una clave de acceso secreto y una clave de verificación pública que coincidan.
4: 09 Ahora damos la clave secreta al retador, al juez. Y damos la clave pública a ambas partes, tanto para el retador como para el atacante. Así que el atacante sólo conoce la información que es pública, él sólo conoce la clave pública. Y su misión será tratar de forjar un mensaje. El retador conoce la clave secreta, por lo que puede hacer firmas. En este momento, si piensas en una aplicación de la vida real, y un atacante de la vida real podría ver firmas válidas de su posible víctima en una serie de documentos diferentes. Y tal vez el atacante podría incluso manipular a la víctima en la firma de documentos de aspecto inocuo, si eso es útil para el atacante. Así que en nuestro juego, vamos a permitir que el atacante obtenga firmas en algunos documentos de su elección. Y vemos eso en el diagrama como este. El atacante va a enviar un mensaje, m0, al retador. Y el retador va a firmar ese mensaje y enviar la firma de vuelta. El atacante puede mirar eso, rascarse la cabeza un poco y enviar otro mensaje, m1. El retador firmará eso. Y hacemos eso por el tiempo que el atacante quiere. El atacante puede enviar sobre cualquier secuencia de mensajes que desee y obtener firmas en ellos.
5: 17 Una vez que el atacante está satisfecho de que haya visto suficientes firmas, y le dejaremos ver sólo un número plausible. Entonces él va a recoger un mensaje m que quiere forjar una firma, y ​​él va a tratar de forjar una firma. Y por supuesto, hay una regla que dice que este m, este mensaje que él está intentando forjar una firma encendido, no es uno de los mensajes que él ha visto ya. Porque sería muy fácil para él enviar una firma válida en m0, quiero decir que le enviamos una firma válida en m0 antes. Así que él va a elegir otro mensaje que no ha visto una firma para todos listo. Y él va a enviar sobre lo que él dice que es una firma en ese mensaje. Ant entonces la pregunta es, ¿puede tener éxito? Así que el desafiador va a ejecutar el algoritmo de verificación, utilizar la clave de verificación pública en ese mensaje, y la firma que el atacante siempre, y va a comprobar si se verifica. Y si se verifica, si esto devuelve verdadero, entonces el atacante gana, el atacante ha forjado un mensaje.
6: 16 Y este juego es lo que utilizamos para definir lo que significa que un esquema de firma digital tenga la propiedad de unforgeablility. Y si queremos ser muy precisos, lo que decimos es que la probabilidad de que el atacante gane este juego es insignificante, y eso es verdad independientemente del algoritmo que el atacante esté usando. En otras palabras, vamos a decir que el esquema de la firma es unforgeable si, no importa qué algoritmo el atacante está utilizando, el atacante tiene solamente una posibilidad insignificante de forjar con éxito un mensaje. Y si tenemos esa propiedad junto con una propiedad mucho más fácil que los mensajes válidos verifican, entonces tenemos un esquema de firma digital que es adecuado.
Bueno, ahora, hay un montón de cosas prácticas que tenemos que hacer para convertir esa idea algorítmica en un mecanismo de firma más prácticamente implementable. Por ejemplo, los algoritmos de los que hablamos son aleatorizados, al menos algunos de ellos lo serán. Y por eso necesitamos una buena fuente de aleatoriedad, y la importancia de esto realmente no se puede subestimar. La aleatoriedad de la banda te hundirá, tu algoritmo será inseguro. Y voy a señalar aquí que los ataques a la fuente de la aleatoriedad son un truco favorito de las agencias de inteligencia. Y esas son las personas que saben qué tipo de ataques es probable que tengan éxito. En la práctica, hay un límite en el tamaño de mensaje que puede firmar porque los esquemas reales van a funcionar en cadenas de bits de longitud limitada. Y la solución a eso es simplemente usar el hash del mensaje en lugar del mensaje en sí. De esta manera el mensaje puede ser muy grande, pero el hash sólo será de 256 bits.
7: 45 Y debido a que las funciones de hash no tienen colisión, es seguro utilizar el hash del mensaje como entrada al esquema de firma digital en lugar del mensaje. Y por cierto un truco divertido que veremos usado más tarde es que usted puede firmar un puntero hash. Y si usted firma un puntero hash, entonces la firma cubre o protege toda la estructura, no sólo el puntero hash mismo, sino todo lo que apunta y todo lo que apunta. Por ejemplo, si firmar el puntero hash que estaba al final de una cadena de bloque, el resultado es que efectivamente firmaría digitalmente todo el contenido de esa cadena de bloque. Ese es un truco útil que veremos usado más tarde.
8:20 Bueno, ahora vamos a entrar en las tuercas y los pernos. Bitcoin utiliza un esquema de firma digital particular que se llama ECDSA. Ese es el Algoritmo de Firma Digital de la Curva Elíptica. Y es un estándar del gobierno estadounidense. Y no vamos a entrar en todos los detalles de cómo funciona ECDSA. Se basa en algunas matemáticas muy peludas. Y confía en mí, no quieres ver todos los detalles de cómo funciona, puedes buscarlo si estás interesado. Así que saltaremos eso. Una cosa que notaré sin embargo, con ECDSA buena aleatoriedad, dije esto antes pero lo diré otra vez porque es realmente esencial. La buena aleatoriedad es especialmente esencial con ECDSA. Si utiliza mala aleatoriedad en la generación de claves o incluso en la firma, probablemente se filtró su clave privada. Es lógico que si utiliza mala aleatoriedad en la generación de una clave que la clave que genera no es segura. Pero es un capricho de ECDSA que incluso si se utiliza mala aleatoriedad sólo en la toma de una firma con su clave perfectamente bien, que también se filtra su clave privada y, a continuación, es el juego más. Por lo tanto, debemos tener especial cuidado con esto en la práctica. Este es un error común. De modo que completa la discusión de las firmas digitales como una primitiva criptográfica. Y en el siguiente segmento, vamos a seguir adelante y hablar de algunas aplicaciones de firmas digitales que resultarán ser útiles en la construcción de cripto-monedas.

