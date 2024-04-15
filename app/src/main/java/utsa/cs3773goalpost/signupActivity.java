package utsa.cs3773goalpost;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import utsa.cs3773goalpost.controller.signupController;

/**
 * sets up the activity_signup.xml and the signupController class
 */
public class signupActivity extends AppCompatActivity {
    private signupController controller;
    private ConnectionClass connectionClass;

    /**
     * set up layout of signup screen
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        controller = new signupController(this);
        connectionClass = ConnectionClass.getInstance();
        //set up the signup button
        setupButton();
            }
    private void setupButton() {

        //Find the button
        Button signupButton = findViewById(R.id.register);
        //set the controller as the click listener for the register button
        signupButton.setOnClickListener(controller);
    }

}




