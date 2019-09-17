import app from 'firebase/app';
import 'firebase/auth';

const config = {
    apiKey: "AIzaSyDbjS-vR1n_WC_VEYU4kiZqd4kx2v9QCRQ",
    authDomain: "cloud-final-41200.firebaseapp.com",
    databaseURL: "https://cloud-final-41200.firebaseio.com",
    projectId: "cloud-final-41200",
    storageBucket: "cloud-final-41200.appspot.com",
    messagingSenderId: "1029293010616"
};

class Firebase {
    constructor() {
        app.initializeApp(config);
        this.GoogleProvider = new app.auth.GoogleAuthProvider();
        this.auth = app.auth();
    }

    doSignInWithGoogle = () =>
        this.auth.signInWithPopup(this.GoogleProvider);

    doSignOut = () => this.auth.signOut();

}

export default Firebase;