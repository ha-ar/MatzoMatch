package com.algorepublic.matzomatch.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.algorepublic.matzomatch.R;
import com.algorepublic.matzomatch.Services.LoginService;
import com.algorepublic.matzomatch.Utils.Constants;
import com.algorepublic.matzomatch.Utils.TinyDB;
import com.androidquery.AQuery;

import java.io.File;

/**
 * Created by waqas on 12/7/15.
 */
public class MyProfile extends Fragment {
    View view;
    static final int ACTION_PICK = 3;
    private AQuery aq;
    TinyDB tinyDB;

    public static MyProfile newInstance() {
        return new MyProfile();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myprofile,container,false);
        aq = new AQuery(getActivity(), view);

        tinyDB = new TinyDB(getActivity());
        final Button manB = (Button)view.findViewById(R.id.man);
        final Button femaleB=(Button)view.findViewById(R.id.women);
        final Button previewSubmit=(Button)view.findViewById(R.id.preview_profile);
        previewSubmit.setBackgroundColor(getContext().getResources().getColor(R.color.default_underline_indicator_selected_color));

        aq.id(R.id.headlines_profile).text(tinyDB.getString(Constants.HeadLine));
        aq.id(R.id.name_profile).text(tinyDB.getString(Constants.FirstName));
        aq.id(R.id.image_1).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Choose Photo!");

                builder.setItems(options, new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int item) {


                        if (options[item].equals("Take Photo"))

                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File f = new File(android.os.Environment.getExternalStorageDirectory(), "userImage.jpg");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                            Log.e("Pik", Uri.fromFile(f).toString());

                            startActivityForResult(intent, 2);


                        } else if (options[item].equals("Choose from Gallery"))

                        {

                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                            getActivity().startActivityForResult(intent, ACTION_PICK);


                        } else if (options[item].equals("Cancel")) {

                            dialog.dismiss();

                        }

                    }

                });

                builder.show();
            }
        });


         aq.id(R.id.image_2).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Choose Photo!");

                builder.setItems(options, new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int item) {


                        if (options[item].equals("Take Photo"))

                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File f = new File(android.os.Environment.getExternalStorageDirectory(), "userImage.jpg");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                            Log.e("Pik", Uri.fromFile(f).toString());

                            startActivityForResult(intent, 2);


                        } else if (options[item].equals("Choose from Gallery"))

                        {

                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                            getActivity().startActivityForResult(intent, ACTION_PICK);


                        } else if (options[item].equals("Cancel")) {

                            dialog.dismiss();

                        }

                    }

                });

                builder.show();
            }
        });
        aq.id(R.id.image_3).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Choose Photo!");

                builder.setItems(options, new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int item) {


                        if (options[item].equals("Take Photo"))

                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File f = new File(android.os.Environment.getExternalStorageDirectory(), "userImage.jpg");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                            Log.e("Pik", Uri.fromFile(f).toString());

                            startActivityForResult(intent, 2);


                        } else if (options[item].equals("Choose from Gallery"))

                        {

                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                            getActivity().startActivityForResult(intent, ACTION_PICK);


                        } else if (options[item].equals("Cancel")) {

                            dialog.dismiss();

                        }

                    }

                });

                builder.show();
            }
        });
        aq.id(R.id.image_4).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Choose Photo!");

                builder.setItems(options, new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int item) {


                        if (options[item].equals("Take Photo"))

                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File f = new File(android.os.Environment.getExternalStorageDirectory(), "userImage.jpg");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                            Log.e("Pik", Uri.fromFile(f).toString());

                            startActivityForResult(intent, 2);


                        } else if (options[item].equals("Choose from Gallery"))

                        {

                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                            getActivity().startActivityForResult(intent, ACTION_PICK);


                        } else if (options[item].equals("Cancel")) {

                            dialog.dismiss();

                        }

                    }

                });

                builder.show();
            }
        });
        aq.id(R.id.image_5).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Choose Photo!");

                builder.setItems(options, new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int item) {


                        if (options[item].equals("Take Photo"))

                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File f = new File(android.os.Environment.getExternalStorageDirectory(), "userImage.jpg");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                            Log.e("Pik", Uri.fromFile(f).toString());

                            startActivityForResult(intent, 2);


                        } else if (options[item].equals("Choose from Gallery"))

                        {

                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                            getActivity().startActivityForResult(intent, ACTION_PICK);


                        } else if (options[item].equals("Cancel")) {

                            dialog.dismiss();

                        }

                    }

                });

                builder.show();
            }
        });
        manB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manB.setBackgroundColor(getContext().getResources().getColor(R.color.default_underline_indicator_selected_color));
                femaleB.setBackgroundColor(getContext().getResources().getColor(R.color.white));
            }
        });
        femaleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                femaleB.setBackgroundColor(getContext().getResources().getColor(R.color.default_underline_indicator_selected_color));
                manB.setBackgroundColor(getContext().getResources().getColor(R.color.white));
            }
        });
        return view;
    }
}
