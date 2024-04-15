package utsa.cs3773goalpost.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import utsa.cs3773goalpost.R;
import utsa.cs3773goalpost.forgotpwActivity;
import utsa.cs3773goalpost.homeActivity;

public class signupController implements View.OnClickListener{
    private final Activity activity;
    public signupController(Activity activity){
        this.activity = activity;
    }


    /**
     * move to next screen if button being clicked
     * @param view
     */
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.register){
            Intent intent = new Intent(activity, homeActivity.class);
            activity.startActivity(intent);
        }
    }


}
