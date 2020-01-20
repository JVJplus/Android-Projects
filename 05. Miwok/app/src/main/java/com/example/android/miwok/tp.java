/*
package com.example.android.miwok;

*/
/**
 * Created by: Jay
 * Date:20-12-2019
 * Time:20:11
 * TODO:
 *//*

public class tp {
}





public class AndroidFlavorAdapter extends ArrayAdapter<AndroidFlavor> {

    private static final String LOG_TAG = AndroidFlavorAdapter.class.getSimpleName();

    public AndroidFlavorAdapter(Activity context, ArrayList<AndroidFlavor> androidFlavors) {
        super(context, 0, androidFlavors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        AndroidFlavor currentAndroidFlavor = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.version_name);
        nameTextView.setText(currentAndroidFlavor.getVersionName());

        TextView numberTextView = (TextView) listItemView.findViewById(R.id.version_number);
        numberTextView.setText(currentAndroidFlavor.getVersionNumber());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        iconView.setImageResource(currentAndroidFlavor.getImageResourceId());

        return listItemView;
    }

}
*/
