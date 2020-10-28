package tn.doctorcode.scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BlankFragment extends Fragment {

    EditText etCodigo;
    Button btnLeerCodigo;

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_blank, container, false);

        // DEFINIMOS LOS COMPONENTES
        etCodigo = vista.findViewById(R.id.etCodigo);
        btnLeerCodigo = vista.findViewById(R.id.btnLeerCodigo);

        btnLeerCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escanear();
            }
        });


        return vista;
    }

    public void escanear() {

        IntentIntegrator intent = IntentIntegrator.forSupportFragment(BlankFragment.this);
        //IntentIntegrator intent = new IntentIntegrator(getActivity());
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setPrompt("ESCANEAR CODIGO");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(getContext(), "Cancelaste el escaneo", Toast.LENGTH_SHORT).show();
            } else {
                etCodigo.setText(result.getContents().toString());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
