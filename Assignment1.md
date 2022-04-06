**Reproduced as is from Felipe Faria's [post](https://www.coursera.org/learn/cryptocurrency/programming/KOo3V/scrooge-coin/discussions/threads/Yu1-ncdjEeaQDgq_dR_6Tg/replies/-DEdGseoEeaQDgq_dR_6Tg)**

The first thing you must do before starting the assignment is understanding the assignment. For obvious reasons I can't type here a "step-by-step procedure" of the assignment, but I can go over the classes and maybe clarify the instructions a bit better for you and anyone else having difficulties.

Your job for this assignment is to write the TxHandler class, which stands for Transactions Handler. This class has three functions in which you must write it yourself; **TxHandler, isValidTx,** and **handleTxs**. As the names suggest, one will initiate the class, one will verify the transactions, and the other will handle the transactions. Now I'm going to assume you have a basic understanding of Java, and you comprehend the idea of classes and functions.

These are the files given to us for the assignment:
```
Crypto.java
Transaction.java
TxHandler.java
UTXO.java
UTXOPool.java
```

And the file we will be writing in is **TxHandler.java**. Let's begin our investigation:

```java
public class TxHandler {

    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */
    public TxHandler(UTXOPool utxoPool) {
        // IMPLEMENT THIS
    }

    /**
     * @return true if:
     * (1) all outputs claimed by {@code tx} are in the current UTXO pool, 
     * (2) the signatures on each input of {@code tx} are valid, 
     * (3) no UTXO is claimed multiple times by {@code tx},
     * (4) all of {@code tx}s output values are non-negative, and
     * (5) the sum of {@code tx}s input values is greater than or equal to the sum of its output
     *     values; and false otherwise.
     */
    public boolean isValidTx(Transaction tx) {
        // IMPLEMENT THIS
    }

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UTXO pool as appropriate.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs) {
        // IMPLEMENT THIS
    }

}
```
This is the class we will be writing on. Clearly our **TxHandler** function initiates the class, **isValid** function is making sure the transaction is valid, and **handleTxs** function is handling multiple transactions. I'm not going into further detail within this class because that's what the assignment is about.

For the sake of explanation, I'm starting from the top-up, explaining the smaller classes that make up the bigger classes. For that, we ought to start with the **Input** and **Output** class within the **Transaction** class:
```java
public class Transaction {

    public class Input {
        /** hash of the Transaction whose output is being used */
        public byte[] prevTxHash;
        /** used output's index in the previous transaction */
        public int outputIndex;
        /** the signature produced to check validity */
        public byte[] signature;

        public Input(byte[] prevHash, int index) {
            if (prevHash == null)
                prevTxHash = null;
            else
                prevTxHash = Arrays.copyOf(prevHash, prevHash.length);
            outputIndex = index;
        }

        public void addSignature(byte[] sig) {
            ...
        }
    }

    public class Output {
        /** value in bitcoins of the output */
        public double value;
        /** the address or public key of the recipient */
        public PublicKey address;

        public Output(double v, PublicKey addr) {
            value = v;
            address = addr;
        }
    }

    ...
}
```
The **Input** and **Output** classes are arguably the most important concept to understand within this whole assignment. You must understand that an **Input** is the receiving of BTC (_BitcCoin_), and an **Output** is a sending of BTC. Every transaction has an **Input** and an **Output**, and every transaction _requires_ an input and an output. The reason a Transaction is created is to send an amount of BTC, but that output must have been received by an input of an earlier date. Transactions keep track of the **Output** and the **Input**. For this you must understand a concept within Bitcoin called **UTXO**, which I explained in a earlier reply to another question:

UTXO stands for Unspent Transaction Output. In cryptocurrency lingo that simply means the output. The way that cryptocurrencies work, specifically Bitcoins, whenever you send an amount of money to another address you utilize past transactions (inputs) send to your address.

These inputs are never actually destroyed when they reach your address, this means that your total Bitcoins is dependent on the sum of all independent inputs to your address. If you receive three transactions of 0.5 BTC, 1.0 BTC, and 0.2 BTC, your sum would be 1.7 BTC... yet your inputs would still be the 0.5, 1.0, and 0.2 BTC. At no point does the independent transactions (inputs) destroy themselves and become the 1.7 BTC exclusively.

With that in mind, UTXO pool keeps track of all your unspent BTC (UTXO). In the example above, it would be the 0.5 BTC, 1.0 BTC, and 0.2 BTC, which is your unspent transaction output. When you do decide to spend a quantity of BTC, your Bitcoin wallet will utilize your UTXOs as inputs for another transaction. Here is an example of these transactions:

```text
Alice output 0.5 BTC to Bob.
Steve output 1.0 BTC to Bob.
Craig output 0.2 BTC to Bob.

Bob receives 0.5 BTC input from Alice.
Bob receives 1.0 BTC input from Steve.
Bob receives 0.2 BTC input from Craig.

These inputs to Bob's wallet become UTXO (unspend transaction outputs) because Bob has yet to use them.

Bob UTXOPool: [0.5, 1.0, 0.2]
```
This is a fundamental point to understand. The input and output of a specify amount of BTC is always kept saved for verification purposes, and this assignment is us basically writing that concept to code. The **Transaction** class simply stores how much we are sending (outputting), and where exactly that money came from to begin with (input). With that in mind, we go to the **UTXO** class:

The **UTXO** class was basically explained above, so I'm not going into much more detail than it is basically the saving of a current amount of BTC. Heading from  **UTXO** to **UTXOPool**, which was also explained above:

```java
public class UTXOPool {

    /**
     * The current collection of UTXOs, with each one mapped to its corresponding transaction output
     */
    private HashMap<UTXO, Transaction.Output> H;

    ...
}
```
The **UTXOPool** class simply keeps track of the **UTXOs**. The final class, before I wrap everything up, is the **Crypto** class, which we will utilize it within the **TxHandler** class to verify the transactions:

```java
public class Crypto {

    /**
     * @return true is {@code signature} is a valid digital signature of {@code message} under the
     *         key {@code pubKey}. Internally, this uses RSA signature, but the student does not
     *         have to deal with any of the implementation details of the specific signature
     *         algorithm
     */
    public static boolean verifySignature(PublicKey pubKey, byte[] message, byte[] signature) {
        ...
    }
}
```
To understand the assignment we must have a mild understanding of some basic cryptography. The idea of _verifying a signature_ follows the logic that we want only the individual who owns the Bitcoin, to actually be allowed to spend it. We do this by having the individual **sign** his or her transaction, and so in the future we can verify if the transaction is valid.

In the Bitcoin world this is done by utilizing _ECDSA (Elliptic Curve Digital Signature Algorithm)_ encryption. In ECDSA we have a private and a public key, and these are both utilized to sign and verify our transactions separately. Your private key (as the name suggests) is yours and yours only, and you utilize this to sign your message. Your public key is utilized to verify the message, and as the name suggests can be handed out to anyone.

Here is an example of this being used:
```
Private Key: 1a56ee1521e09f89814524342df0aaa3838a6b7d69d7fc7296a0b7fe45aeec7a
Public Key: 046df9a102a81a27b701dd9c50faf096413fa2fbf05c5c43108ffb292592cfa2723d73ad2b9da6ce7d3a2354e27010eed42001667e920a2baf358828f8a8796310

Message: Hello, this is an example!
Signature Value: 3045022026c9e94ad512060a4b4f8b19fc42d2f08c0749de493afdd8fcce157b8b05bb74022100e2c9d301f4559edb075fc5b399e371cbfa704b49a10a64835ff5532ab5a8c844
```

If you were to go [here](https://kjur.github.io/jsrsasign/sample-ecdsa.html), and input the public key, the message, and the signature value, you will notice that we receive a verified output. Change the message, the public key, or the signature value, and now we get a invalid output. The idea behind ECDSA is that for you to generate the signature value of a specific message, you require a private key. This private/public key interaction is extremely important to guarantee that only you have access to your Bitcoins, and no one else can create fake transactions under your identity.

The function **verifySignature** takes in a public key, the raw message, and the signature value of that raw message. With the three inputs, it can then verify if the signature corresponds to the public key and the message, and either validates or invalidates the transaction.

So to wrap everything up, this is whats going on:

I own a **UTXOPool** full of BTC inputs from past transactions. When I want to send some BTC over to Bob, I'll create a new transaction, filling its input and output details, signing it to verify my identity, and sending it over to the network for processing. Your job is to write the **TxHandler** class which will validate the transaction, and process transactions (updating the UTXOPool of the user creating the transaction).
