package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashingUtility {

    private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(10);

    public static String hash(String password) {
        return bcrypt.hash(password);
    }

    public static boolean verifyHash(String password, String hash){
        return bcrypt.verifyHash(password, hash);
    }

    private static class UpdatableBCrypt{
        private final static Logger log = LogManager.getLogger(DBConnectionManager.class);
        private final int logRounds;

        public UpdatableBCrypt(int logRounds) {
            this.logRounds = logRounds;
        }

        public String hash(String password) {
            return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
        }

        public boolean verifyHash(String password, String hash) {
            return BCrypt.checkpw(password, hash);
        }

        /*
         * Copy pasted from BCrypt internals :(. Ideally a method
         * to exports parts would be public. We only care about rounds
         * currently.
         */
        private int getRounds(String salt) {
            char minor = (char)0;
            int off = 0;
            if (salt.charAt(0) != '$' || salt.charAt(1) != '2')
                throw new IllegalArgumentException ("Invalid salt version");
            if (salt.charAt(2) == '$')
                off = 3;
            else {
                minor = salt.charAt(2);
                if (minor != 'a' || salt.charAt(3) != '$')
                    throw new IllegalArgumentException ("Invalid salt revision");
                off = 4;
            }
            // Extract number of rounds
            if (salt.charAt(off + 2) > '$')
                throw new IllegalArgumentException ("Missing salt rounds");
            return Integer.parseInt(salt.substring(off, off + 2));
        }
    }
}

