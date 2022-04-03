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





