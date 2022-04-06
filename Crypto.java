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
