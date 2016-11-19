package com.mio.jrdv.kidstimergenerator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class MainActivity extends AppCompatActivity {

    //para las fotos de los ninos:

    private ImageView MiFotoHijo1;
    private ImageView MiFotoHijo2;
    private ImageView MiFotoHijo3;

    private  boolean FotoYatomadakid1;
    private  boolean FotoYatomadakid2;
    private  boolean FotoYatomadakid3;

    private String FotoPathkid1;
    private String FotoPathkid2;
    private String FotoPathkid3;

    private String TAGCameraKid;

    //Referencing EditText placed inside in xml layout file
    EditText ChildrenName ;

    //para el dialog del nombe

    final Context c = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //To hide AppBar for fullscreen.
        ActionBar ab = getSupportActionBar();
        ab.hide();

        //Referencing EditText widgets and Button placed inside in xml layout file
        ChildrenName = (EditText) findViewById(R.id.txtname_check_children);


        //para la foto:

        MiFotoHijo1 = (ImageView) findViewById(R.id.kidsreg1);

        MiFotoHijo2 = (ImageView) findViewById(R.id.kidsreg2);
        MiFotoHijo3 = (ImageView) findViewById(R.id.kidsreg3);


        EasyImage.configuration(this)
                .setImagesFolderName("KidsTimer") //images folder name, default is "EasyImage"
                //.saveInAppExternalFilesDir() //if you want to use root internal memory for storying images
                .saveInRootPicturesDirectory(); //if you want to use internal memory for storying images - default


        //al principio aun no nhay foto

        FotoYatomadakid1 = false;
        FotoYatomadakid2 = false;
        FotoYatomadakid3 = false;

        //pongo 1 timer para que alos 2 seg cambie el icono de PADRE/HIJOpor la camera


        new CountDownTimer(1000, 1000) {//delay 1 seg y tarda 1 seg

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {



                //1º)chequeamos si tenemos path de la foto guardada


                String Fotokid1path = Myapplication.preferences.getString(Myapplication.PREF_FOTO_PATH_KID1,"NONE");//por defecto vale 0

                if (!Fotokid1path.equals("NONE")){

                    //si tiene foto
                    FotoYatomadakid1 = true;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    Bitmap bitmap2=BitmapFactory.decodeFile(Fotokid1path,options);

                    MiFotoHijo1.setImageBitmap(Bitmap.createScaledBitmap(bitmap2,200,200,true));

                    //lo hacemos animado mejo!!

                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce); // Use bounce interpolator with amplitude 0.2 and frequency 20
                    BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
                    myAnim.setInterpolator(interpolator);
                    MiFotoHijo1.startAnimation(myAnim);

                }


                //IDEM KID2


                //1º)chequeamos si tenemos path de la foto guardada


                String Fotokid2path = Myapplication.preferences.getString(Myapplication.PREF_FOTO_PATH_KID2,"NONE");//por defecto vale 0

                if (!Fotokid2path.equals("NONE")){

                    //si tiene foto
                    FotoYatomadakid2 = true;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    Bitmap bitmap2=BitmapFactory.decodeFile(Fotokid1path,options);

                    MiFotoHijo2.setImageBitmap(Bitmap.createScaledBitmap(bitmap2,200,200,true));

                    //lo hacemos animado mejo!!

                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce); // Use bounce interpolator with amplitude 0.2 and frequency 20
                    BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
                    myAnim.setInterpolator(interpolator);
                    MiFotoHijo2.startAnimation(myAnim);

                }


                //IDEM KID3

                //1º)chequeamos si tenemos path de la foto guardada


                String Fotokid3path = Myapplication.preferences.getString(Myapplication.PREF_FOTO_PATH_KID3,"NONE");//por defecto vale 0

                if (!Fotokid3path.equals("NONE")){

                    //si tiene foto
                    FotoYatomadakid3 = true;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    Bitmap bitmap2=BitmapFactory.decodeFile(Fotokid1path,options);

                    MiFotoHijo3.setImageBitmap(Bitmap.createScaledBitmap(bitmap2,200,200,true));

                    //lo hacemos animado mejo!!

                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce); // Use bounce interpolator with amplitude 0.2 and frequency 20
                    BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
                    myAnim.setInterpolator(interpolator);
                    MiFotoHijo3.startAnimation(myAnim);

                }


            }
        }.start();



    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////FOTOS////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void TomarFotoHijo(View view) {


        if (!FotoYatomadakid1) {


            //1º dialog para nonbre

            LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
            View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
            AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
            alertDialogBuilderUserInput.setView(mView);


            final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
            alertDialogBuilderUserInput
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBox, int id) {
                            // ToDo get user input here

                            //1º)chequeamos si el nombre si esta bien

                            if (userInputDialogEditText.getText().toString().isEmpty() || userInputDialogEditText.getText().toString().length() <4 || userInputDialogEditText.getText().toString().length()>10){

                                //esta mal el nombre
                                dialogBox.cancel();
                            }

                            else {

                                //1º)guaradmos el nombre

                                Myapplication.preferences.edit().putString(Myapplication.PREF_NAME_KID1,userInputDialogEditText.getText().toString()).commit();
                                //2º)la foto


                                Log.d("INFO", "tomando foto kid 1");

                                TAGCameraKid = "KID1";

                                //  FotoYatomada=true;//no meor en el activityresult qeu asi seguro que si la tiene!!
                                //EasyImage.
                                EasyImage.openChooserWithGallery(MainActivity.this, "CHOOSE PICTURE", 0);
                            }

                        }
                    })

                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogBox, int id) {
                                    dialogBox.cancel();
                                }
                            });

            AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
            alertDialogAndroid.show();




        }
        else {

            //ya tine foto ponemos elm nombre ene l edittext

            //ChildrenName.setText("GUSTAVO");

            //idem con pref

            String kid1NMameFromPref = Myapplication.preferences.getString(Myapplication.PREF_NAME_KID1,"NONE");//por defecto vale 0

            ChildrenName.setText(kid1NMameFromPref);


        }


    }


    public void TomarFotoHijo2(View view) {

        if (!FotoYatomadakid2) {
            //1º dialog para nonbre

            LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
            View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
            AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
            alertDialogBuilderUserInput.setView(mView);


            final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
            alertDialogBuilderUserInput
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBox, int id) {
                            // ToDo get user input here

                            //1º)chequeamos si el nombre si esta bien

                            if (userInputDialogEditText.getText().toString().isEmpty() || userInputDialogEditText.getText().toString().length() <4 || userInputDialogEditText.getText().toString().length()>10){

                                //esta mal el nombre
                                dialogBox.cancel();
                            }

                            else {

                                //1º)guaradmos el nombre

                                Myapplication.preferences.edit().putString(Myapplication.PREF_NAME_KID2,userInputDialogEditText.getText().toString()).commit();
                                //2º)la foto


                                Log.d("INFO", "tomando foto kid 2");

                                TAGCameraKid = "KID2";

                                //  FotoYatomada=true;//no meor en el activityresult qeu asi seguro que si la tiene!!
                                //EasyImage.
                                EasyImage.openChooserWithGallery(MainActivity.this, "CHOOSE PICTURE", 0);
                            }

                        }
                    })

                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogBox, int id) {
                                    dialogBox.cancel();
                                }
                            });

            AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
            alertDialogAndroid.show();

        }
        else {

            //ya tine foto ponemos elm nombre ene l edittext

           //ChildrenName.setText("INMA");

            //idem con pref

            String kid2NMameFromPref = Myapplication.preferences.getString(Myapplication.PREF_NAME_KID2,"NONE");//por defecto vale 0

            ChildrenName.setText(kid2NMameFromPref);

        }


    }


    public void TomarFotoHijo3(View view) {


        if (!FotoYatomadakid3) {

            //1º dialog para nonbre

            LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
            View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
            AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
            alertDialogBuilderUserInput.setView(mView);


            final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
            alertDialogBuilderUserInput
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBox, int id) {
                            // ToDo get user input here

                            //1º)chequeamos si el nombre si esta bien

                            if (userInputDialogEditText.getText().toString().isEmpty() || userInputDialogEditText.getText().toString().length() < 4 || userInputDialogEditText.getText().toString().length() > 10) {

                                //esta mal el nombre
                                dialogBox.cancel();
                            } else {

                                //1º)guaradmos el nombre

                                Myapplication.preferences.edit().putString(Myapplication.PREF_NAME_KID3, userInputDialogEditText.getText().toString()).commit();
                                //2º)la foto


                                Log.d("INFO", "tomando foto kid 3");

                                TAGCameraKid = "KID3";

                                //  FotoYatomada=true;//no meor en el activityresult qeu asi seguro que si la tiene!!
                                //EasyImage.
                                EasyImage.openChooserWithGallery(MainActivity.this, "CHOOSE PICTURE", 0);
                            }

                        }
                    })

                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogBox, int id) {
                                    dialogBox.cancel();
                                }
                            });

            AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
            alertDialogAndroid.show();
        }
        else {

            //ya tine foto ponemos elm nombre ene l edittext

            //ChildrenName.setText("NONAME");

            //idem con pref

            String kid3NMameFromPref = Myapplication.preferences.getString(Myapplication.PREF_NAME_KID3,"NONE");//por defecto vale 0

            ChildrenName.setText(kid3NMameFromPref);

        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagesPicked(List<File> imageFiles, EasyImage.ImageSource source, int type) {
                //onPhotosReturned(imageFiles);
                Log.d("INFO", "foto cogida ok");


                //convierto el file en bitmap:


                //para evitar problemas de moemoria:
                //http://stackoverflow.com/questions/11820266/android-bitmapfactory-decodestream-out-of-memory-with-a-400kb-file-with-2mb-f
                //http://stackoverflow.com/questions/32244851/androidjava-lang-outofmemoryerror-failed-to-allocate-a-23970828-byte-allocatio
                //Bitmap bitmap = BitmapFactory.decodeFile(imageFiles.get(0).getPath());

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap bitmap2=BitmapFactory.decodeFile(imageFiles.get(0).getPath(),options);


                switch (TAGCameraKid){


                    case "KID1":
                        // MiFotoHijo.setImageBitmap(bitmap); //da errores de memoria ,asi que ponog una mas pequeña...
                        MiFotoHijo1.setImageBitmap(Bitmap.createScaledBitmap(bitmap2,200,200,true));
                         //guardamos el path en nuestra property
                          FotoPathkid1 = (imageFiles.get(0).getPath());
                        FotoYatomadakid1 = true;
                        //  guardamos el path en pref

                        Myapplication.preferences.edit().putString(Myapplication.PREF_FOTO_PATH_KID1,FotoPathkid1).commit();


                        break;

                    case "KID2":
                        // MiFotoHijo.setImageBitmap(bitmap); //da errores de memoria ,asi que ponog una mas pequeña...
                        MiFotoHijo2.setImageBitmap(Bitmap.createScaledBitmap(bitmap2,200,200,true));
                        //guardamos el path en nuestra property
                        FotoPathkid2 = (imageFiles.get(0).getPath());
                        FotoYatomadakid2 = true;
                        //  guardamos el path en pref

                          Myapplication.preferences.edit().putString(Myapplication.PREF_FOTO_PATH_KID2,FotoPathkid2).commit();


                        break;

                    case "KID3":
                        // MiFotoHijo.setImageBitmap(bitmap); //da errores de memoria ,asi que ponog una mas pequeña...
                        MiFotoHijo3.setImageBitmap(Bitmap.createScaledBitmap(bitmap2,200,200,true));
                        //guardamos el path en nuestra property
                        FotoPathkid3 = (imageFiles.get(0).getPath());
                        FotoYatomadakid3 = true;
                        //  guardamos el path en pref
                        Myapplication.preferences.edit().putString(Myapplication.PREF_FOTO_PATH_KID3,FotoPathkid3).commit();


                        break;


                }



              //  guardamos el path en pref



                Myapplication.preferences.edit().putString(Myapplication.PREF_FOTO_PATH_KID1,FotoPathkid1).commit();





            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(MainActivity.this);
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
    }

}
