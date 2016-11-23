package io.geeteshk.hyper.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import io.geeteshk.hyper.R;

public class AttrsAdapter extends RecyclerView.Adapter<AttrsAdapter.AttrsHolder> {

    public Element element;
    public List<String> allAttributes;
    public Context context;

    private final Pattern patternBuiltins = Pattern.compile("\\b(charset|lang|href|name|target|onclick|onmouseover|onmouseout|accesskey|code|codebase|width|height|align|vspace|hspace|border|name|archive|mayscript|alt|shape|coords|target|nohref|size|color|face|src|loop|bgcolor|background|text|vlink|alink|bgproperties|topmargin|leftmargin|marginheight|marginwidth|onload|onunload|onfocus|onblur|stylesrc|scroll|clear|type|value|valign|span|compact|pluginspage|pluginurl|hidden|autostart|playcount|volume|controls|controller|mastersound|starttime|endtime|point-size|weight|action|method|enctype|onsubmit|onreset|scrolling|noresize|frameborder|bordercolor|cols|rows|framespacing|border|noshade|longdesc|ismap|usemap|lowsrc|naturalsizeflag|nosave|dynsrc|controls|start|suppress|maxlength|checked|language|onchange|onkeypress|onkeyup|onkeydown|autocomplete|prompt|for|rel|rev|media|direction|behaviour|scrolldelay|scrollamount|http-equiv|content|gutter|defer|event|multiple|readonly|cellpadding|cellspacing|rules|bordercolorlight|bordercolordark|summary|colspan|rowspan|nowrap|halign|disabled|accesskey|tabindex|id|class)");

    public AttrsAdapter(Context context, Element element) {
        this.context = context;
        this.element = element;
        allAttributes = Arrays.asList(patternBuiltins.pattern().replace("(", "").replace(")", "").substring(2, patternBuiltins.pattern().length() - 2).split("\\|"));
        Collections.sort(allAttributes);
    }

    @Override
    public AttrsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attr, parent, false);
        return new AttrsHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final AttrsHolder holder, final int position) {
        final int newPos = holder.getAdapterPosition();
        holder.attrKey.setText(allAttributes.get(newPos));
        holder.attrValue.setText(element.attr(allAttributes.get(newPos)));
        holder.attrView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(allAttributes.get(newPos));
                final EditText editText = new EditText(context);
                editText.setHint("Value");
                editText.setSingleLine(true);
                editText.setMaxLines(1);
                editText.setText(element.attr(allAttributes.get(newPos)));
                builder.setView(editText);
                builder.setPositiveButton("SET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        element.attr(allAttributes.get(newPos), editText.getText().toString());
                        holder.attrValue.setText(editText.getText().toString());
                    }
                });

                builder.setNegativeButton("CANCEL", null);
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return allAttributes.size();
    }

    public class AttrsHolder extends RecyclerView.ViewHolder {
        public TextView attrKey, attrValue;
        public View attrView;

        public AttrsHolder(View view) {
            super(view);
            attrView = view;
            attrKey = (TextView) view.findViewById(R.id.attr_key);
            attrValue = (TextView) view.findViewById(R.id.attr_value);
        }
    }
}