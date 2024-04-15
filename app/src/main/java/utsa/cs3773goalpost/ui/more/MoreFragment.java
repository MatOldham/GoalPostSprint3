// This class is a fragment that displays the preferences.

package utsa.cs3773goalpost.ui.more;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import utsa.cs3773goalpost.ConnectionClass;
import utsa.cs3773goalpost.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.SwitchPreferenceCompat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utsa.cs3773goalpost.databinding.FragmentMoreBinding;
import utsa.cs3773goalpost.loginActivity;

public class MoreFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {


    private FragmentMoreBinding binding;

    public static MoreFragment newInstance() {

        return new MoreFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(MoreViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        Preference changeUsernamePref = findPreference("change_username");
        Preference changePasswordPref = findPreference("change_password");
        Preference logoutPref = findPreference("logout");
        Preference deleteAccountPref = findPreference("delete_account");
        CheckBoxPreference notificationsPref = findPreference("notifications");
        SwitchPreferenceCompat themePref = findPreference("theme");

        changeUsernamePref.setOnPreferenceClickListener(this);
        changePasswordPref.setOnPreferenceClickListener(this);
        logoutPref.setOnPreferenceClickListener(this);
        deleteAccountPref.setOnPreferenceClickListener(this);
        notificationsPref.setOnPreferenceChangeListener(this);
        themePref.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        // This handles click events for preference items
        String key = preference.getKey();
        switch (key) {
            case "change_username":
                //openChangeUsernameDialog();
                return true;
            case "change_password":
                openChangePasswordDialog();
                return true;
            case "logout":
                performLogout();
                return true;
            case "delete_account":
                openDeleteAccountDialog();
                return true;
        }
        return true;
    }

//    private void openChangeUsernameDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
//        builder.setTitle("Change Username");
//
//        // Set up the input
//        final EditText input = new EditText(requireContext());
//        input.setInputType(InputType.TYPE_CLASS_TEXT);
//        builder.setView(input);
//
//        // Set up the buttons
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String newUsername = input.getText().toString().trim();
//                // validate the new username
//                if (!TextUtils.isEmpty(newUsername)) {
//                    ConnectionClass connectionClass = new ConnectionClass();
//                    Connection conn = connectionClass.CONN();
//                    if (conn != null) {
//                        try {
//                            String query = "UPDATE users SET username = ? WHERE user_id = ?";
//                            PreparedStatement preparedStatement = conn.prepareStatement(query);
//                            preparedStatement.setString(1, newUsername);
//                            //preparedStatement.setInt(2, userId); // replace userId with the actual user's ID
//                            int rowsAffected = preparedStatement.executeUpdate();
//                            if (rowsAffected > 0) {
//                                // username updated successfully
//                                Toast.makeText(requireContext(), "Username updated successfully", Toast.LENGTH_SHORT).show();
//                            } else {
//                                // failed to update username
//                                Toast.makeText(requireContext(), "Failed to update username", Toast.LENGTH_SHORT).show();
//                            }
//                            preparedStatement.close();
//                            conn.close();
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                            // Handle SQL exception
//                        }
//                    } else {
//                        // failed to connect to database
//                        Toast.makeText(requireContext(), "Failed to connect to database", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    // show error message if username is empty
//                    Toast.makeText(requireContext(), "Username cannot be empty", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        // show dialog
//        builder.show();
//    }

    private void openChangePasswordDialog() {
    }

    private void performLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(requireActivity(), loginActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void openDeleteAccountDialog() {
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        if ("notifications".equals(key)) {
            // Handle notification preference change
            boolean isChecked = (boolean) newValue;
            // Implement logic to enable/disable notifications
            return true; // Indicate that the change has been handled
        } else if ("theme".equals(key)) {
            // Handle theme preference change
            boolean isChecked = (boolean) newValue;
            // Switch between light mode and dark mode based on isChecked value
            int mode = isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
            AppCompatDelegate.setDefaultNightMode(mode);
            requireActivity().recreate(); // Recreate activity to apply theme change
            return true; // Indicate that the change has been handled
        }
        return false; // Indicate that the change has not been handled
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}