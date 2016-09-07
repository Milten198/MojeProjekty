package com.example.android.contactlist.data;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

import com.example.android.contactlist.Data.ContactContract;
import com.example.android.contactlist.Data.ContactDbHelper;
import com.example.android.contactlist.Data.ContactProvider;

public class TestProvider extends ProviderTestCase2<ContactProvider> {

    private static MockContentResolver resolver;
    Uri uri = ContactContract.ContactEntry.CONTENT_URI;

    public TestProvider() {
        super(ContactProvider.class, ContactContract.CONTENT_AUTHORITY);
    }

    public void deleteDatabase() {
        mContext.deleteDatabase(ContactContract.ContactEntry.TABLE_NAME);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        deleteDatabase();
        resolver = this.getMockContentResolver();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInsert() {
        insertRow();
    }

    public void testQuery() {
        Cursor cursor = insertRow();
        cursor.moveToFirst();
        int position = cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_USER_NAME);
        String name = cursor.getString(position);
        assertTrue("Wrong name", name.equals("John"));
    }

    public void testDelete() {
        insertRow();
        int deletedRows = resolver.delete(ContactContract.ContactEntry.CONTENT_URI, null, null);
        assertTrue("deleteRows was 0", deletedRows == 1);
    }

    private Cursor insertRow() {
        ContentValues values = TestUtils.loadData();
        resolver.insert(ContactContract.ContactEntry.CONTENT_URI, values);
        Cursor cursor = resolver.query(ContactContract.ContactEntry.CONTENT_URI, null, null, null, null);
        assert cursor != null;
        assertTrue("Cursor count is wrong", cursor.getCount() == 1);

        return cursor;
    }


}
