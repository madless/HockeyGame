package com.example.student.hockeygame.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.student.hockeygame.R;
import com.example.student.hockeygame.adapter.HockeyGameListAdapter;
import com.example.student.hockeygame.app_constant.TransferConstant;
import com.example.student.hockeygame.db.DatabaseManager;
import com.example.student.hockeygame.db.dao.TableItemDAO;
import com.example.student.hockeygame.entity.ListItem;
import com.example.student.hockeygame.entity.TableItem;
import com.example.student.hockeygame.listener.OnListItemClickListener;
import com.example.student.hockeygame.listener.RecyclerItemClickListener;

import java.util.List;

public abstract class HockeyGameListActivity extends AppCompatActivity implements OnListItemClickListener {
    protected RecyclerView recyclerView;
    protected List<ListItem> listItems;
    protected HockeyGameListAdapter hockeyGameListAdapter;
    protected DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, this));
    }

    @Override
    public void onListItemLongClick(View view, int position) {
        RemoveListItemDialogFragment removeListItemDialogFragment = new RemoveListItemDialogFragment();
        Bundle arg = new Bundle();
        arg.putInt(TransferConstant.CURRENT_ITEM_POS.toString(), position);
        removeListItemDialogFragment.setArguments(arg);
        removeListItemDialogFragment.show(getSupportFragmentManager(), "tag");
    }

    public class RemoveListItemDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final int pos = getArguments().getInt(TransferConstant.CURRENT_ITEM_POS.toString());

            AlertDialog.Builder ad = new AlertDialog.Builder(HockeyGameListActivity.this);
            ad.setMessage(R.string.dialog_media_removing_message);
            ad.setPositiveButton(R.string.dialog_media_removing_remove_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    TableItemDAO tableItemDAO = getListItemDAO();
                    tableItemDAO.deleteTableItem((TableItem) listItems.get(pos));
                    listItems.remove(pos);
                    hockeyGameListAdapter.notifyItemRemoved(pos);
                    Toast.makeText(HockeyGameListActivity.this, R.string.toast_media_removed_message, Toast.LENGTH_SHORT).show();
                }
            });
            ad.setNegativeButton(R.string.dialog_cancel_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            });
            return ad.create();
        }
    }

    protected abstract TableItemDAO getListItemDAO();
}
