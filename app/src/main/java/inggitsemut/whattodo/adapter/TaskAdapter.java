package inggitsemut.whattodo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import inggitsemut.whattodo.R;
import inggitsemut.whattodo.models.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> data;
    private Context mCtx;

    public TaskAdapter(List<Task> data, Context mCtx) {
        this.data = data;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_task, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder holder, int position) {

        final Task task = data.get(position);
        holder.tvTitle.setText(task.getTitle());
        holder.tvDetail.setText(task.getDetail());

        if (task.getType()==1){
            holder.colorStatus.setBackgroundResource(R.color.red);
        }
        else if (task.getType()==2){
            holder.colorStatus.setBackgroundResource(R.color.yellow);
        }
        else {
            holder.colorStatus.setBackgroundResource(R.color.green);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle, tvDetail;
        private LinearLayout colorStatus;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDetail = itemView.findViewById(R.id.tvDetail);
            colorStatus = itemView.findViewById(R.id.colorStatus);

        }
    }
}
