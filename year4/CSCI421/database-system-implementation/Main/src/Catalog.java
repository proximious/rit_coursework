import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Catalog {

    public static final int INTEGER = 0;
    public static final int DOUBLE = 1;
    public static final int BOOLEAN = 2;
    public static final int CHAR = 3;
    public static final int VARCHAR = 4;

    public static final String READ = "r";
    public static final String WRITE = "rw";

    private final String path;
    private final int pageSize;
    private ArrayList<TableSchema> tables = new ArrayList<>();
    private HashMap<String, TableSchema> tableNameToTableSchema = new HashMap<String, TableSchema>();
    public RandomAccessFile raf;

    public Catalog(String path, int pageSize) {
        this.path = path;
        this.pageSize = pageSize;
    }

    /**
     * Reads a catalog from the physical file 
     */
    public void readCatalog() {
        try {
            String catalogPath = this.path;
            if (path.contains("\\")) {
                catalogPath += "\\Catalog";
            } else {
                catalogPath += "/Catalog";
            }

            File file = new File(catalogPath);

            RandomAccessFile catalogRAFile = new RandomAccessFile(file, READ);
            catalogRAFile.seek(0);
            int numOfTables = catalogRAFile.readInt();
            TableSchema.setLASTTABLEID(numOfTables);

            for (int i = 0; i < numOfTables; i++) {
                // first create the table schema for the class
                TableSchema t = createTableSchemaObjectFromBytes(catalogRAFile);
                
                // add the table schema to the hashmap
                tables.add(t);
                tableNameToTableSchema.put(t.getName(), t);
            }
        } catch (IOException e) {
            System.out.println("File doesnt exist.");
            e.printStackTrace();
        }
    }

    /**
     * returns the hashmap of table names to table schemas
     * @return
     */
    public HashMap<String, TableSchema> getTableNameToTableSchema()
    {
        return this.tableNameToTableSchema;   
    }

    /**
     * returns the tables
     * @return
     */
    public ArrayList<TableSchema> getTables()
    {
        return this.tables;
    }

    /**
     * Checks to see if the catalog file initially exists
     * This is used to determine whether we have to read the existing catalog or not
     */
    public boolean checkExistance()
    {
        String catalogPath = this.path;
        if (this.path.contains("\\")) 
        {
            catalogPath += "\\Catalog";
        } else {
            catalogPath += "/Catalog";
        }

        File catalogFile = new File(catalogPath);

        if (catalogFile.exists() && !catalogFile.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Goes through the physical table file and seeks through memory to get an
     * individual page
     * 
     * @param path path for the table file
     * @param tableName name of the table to get the page from
     * @param pageID id of the page we need
     * @param pageSize given page size from user to 
     * @param attributes attributes to know when to create 
     * @return
     */
    public static Page readIndividualPageFromMemory(String path, String tableName, int pageID, int pageSize, ArrayList<Attribute> attributes)
    {
        try {
            String tablePath = path;
            if (path.contains("\\")) {
                tablePath += "\\" + tableName;
            } else {
                tablePath += "/" + tableName;
            }

            File file = new File(tablePath);
            RandomAccessFile raFile = new RandomAccessFile(file, READ);
            // passing the number of pages in file
            raFile.seek(4);

            // seeking to the page number
            // i.e trying to find page 4 means we have to seek pageSize - 1 * 4
            if(pageID != 1){
                raFile.read(new byte[((pageID - 1) * (pageSize + 8))]);
            }
            raFile.read(new byte[4]); // skip the table id

            int numberOfRecords = raFile.readInt();
            ArrayList<Record> records = new ArrayList<>();

            // iterating over the individual records
            for (int j = 0; j < numberOfRecords; j++) {
                ArrayList<Object> recordData = new ArrayList<>();

                for (int k = 0; k < attributes.size(); k++) {
                    switch (attributes.get(k).getType()) {
                        case BOOLEAN:
                            boolean b = raFile.readBoolean();
                            recordData.add(b);
                            break;
                        case CHAR:
                            int n = attributes.get(k).getN();
                            char[] ch = new char[n];

                            for (int l = 0; l < n; l++) {
                                char c = raFile.readChar();
                                ch[l] = c;
                            }
                            recordData.add(new String(ch));
                            break;
                        case DOUBLE:
                            double d = raFile.readDouble();
                            recordData.add(d);
                            break;
                        case INTEGER:
                            int in = raFile.readInt();
                            recordData.add(in);
                            break;
                        case VARCHAR:
                            int vn = raFile.readInt();
                            char[] vch = new char[vn];

                            for (int l = 0; l < vn; l++) {
                                char c = raFile.readChar();
                                vch[l] = c;
                            }
                            recordData.add(new String(vch));
                            break;
                        default:
                            // program kills itself
                            break;
                    }
                }
                records.add(new Record(recordData, attributes, true));
            }
            raFile.close();
            return new Page(pageID, records);
            
        } catch (IOException e) {
            System.out.println("messed up while reading individual page from memory");
            e.printStackTrace();
        }
        return null; 
    }

    /**
     * reading all the pages from a given table file and returning the pages from each of them
     * @param raFile
     * @param attributes
     * @return
     */
    public ArrayList<Page> readPagesFromTableFile(RandomAccessFile raFile, ArrayList<Attribute> attributes) {
        ArrayList<Page> pages = new ArrayList<>();

        // first getting the number of pages from table file
        try {
            int numOfPages = raFile.readInt();
            PageBuffer.setLASTTABLEID(numOfPages);

            // iterating over all the pages in the file
            for (int i = 0; i < numOfPages; i++) {
                int traversedBytes = 8;
                int pageID = raFile.readInt();
                int numberOfRecords = raFile.readInt();
                ArrayList<Record> records = new ArrayList<>();

                // iterating over the individual records
                for (int j = 0; j < numberOfRecords; j++) {
                    ArrayList<Object> recordData = new ArrayList<>();

                    for (int k = 0; k < attributes.size(); k++) {
                        switch (attributes.get(k).getType()) {
                            case BOOLEAN:
                                boolean b = raFile.readBoolean();
                                traversedBytes += 1;
                                recordData.add(b);
                                break;
                            case CHAR:
                                int n = attributes.get(k).getN();
                                char[] ch = new char[n];

                                for (int l = 0; l < n; l++) {
                                    char c = raFile.readChar();
                                    ch[l] = c;
                                }
                                traversedBytes += (Character.BYTES * n);
                                recordData.add(new String(ch));
                                break;
                            case DOUBLE:
                                double d = raFile.readDouble();
                                traversedBytes += Double.BYTES;
                                recordData.add(d);
                                break;
                            case INTEGER:
                                int in = raFile.readInt();
                                traversedBytes += Integer.BYTES;
                                recordData.add(in);
                                break;
                            case VARCHAR:
                                int vn = raFile.readInt();
                                char[] vch = new char[vn];

                                for (int l = 0; l < vn; l++) {
                                    char c = raFile.readChar();
                                    vch[l] = c;
                                }
                                traversedBytes += (Character.BYTES * vn) + Integer.BYTES;
                                recordData.add(new String(vch));
                                break;
                            default:
                                // program kills itself
                                break;
                        }
                    }
                    records.add(new Record(recordData, attributes, true));
                }
                Page page = new Page(pageID, records);
                pages.add(page);

                raFile.read(new byte[pageSize - traversedBytes]);
            }
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
        }
        return pages;
    }

    /**
     * Reads from binary data to create a TableSchema object
     * @param f the table file we are reading from
     * @return
     */
    public TableSchema createTableSchemaObjectFromBytes(RandomAccessFile f) {
        try {
            // getting the tableID
            int tableID = f.readInt();

            // getting the table name
            int tableNameLength = f.readInt();

            char[] tableNameChars = new char[tableNameLength];

            for (int i = 0; i < tableNameLength; i++) {
                tableNameChars[i] = f.readChar();
            }
            String tableName = new String(tableNameChars);

            // getting the number of pages related to that table
            int numberOfPages = f.readInt();

            // getting the pageIDs
            ArrayList<Integer> pageIDs = new ArrayList<>();

            for (int i = 0; i < numberOfPages; i++) {
                pageIDs.add(f.readInt());
            }

            // getting the attributes from the bytes
            int numberOfAttributes = f.readInt();

            ArrayList<Attribute> attributes = new ArrayList<>();

            for (int i = 0; i < numberOfAttributes; i++) {
                int attributeNameLength = f.readInt();

                char[] attributeNameChars = new char[attributeNameLength];

                for (int j = 0; j < attributeNameLength; j++) {
                    attributeNameChars[j] = f.readChar();
                }

                String attributeName = new String(attributeNameChars);

                int attributeTypeInt = f.readInt();

                Type attributeType;

                switch (attributeTypeInt) {
                    case Catalog.INTEGER -> attributeType = Type.INTEGER;
                    case Catalog.DOUBLE -> attributeType = Type.DOUBLE;
                    case Catalog.BOOLEAN -> attributeType = Type.BOOLEAN;
                    case Catalog.CHAR -> attributeType = Type.CHAR;
                    case Catalog.VARCHAR -> attributeType = Type.VARCHAR;
                    default -> attributeType = Type.INTEGER;
                }

                int attributeN = f.readInt();

                boolean attributeIsPrimaryKey = f.readBoolean();
                boolean attributeNotNull = f.readBoolean();
                boolean attributeUnique = f.readBoolean();

                Attribute attr = new Attribute(attributeName, attributeType, attributeIsPrimaryKey, attributeNotNull, attributeUnique, attributeN);

                attributes.add(attr);
            }
            return new TableSchema(tableID, tableName, attributes, pageIDs.toArray(new Integer[pageIDs.size()]));
        } catch (IOException e) {
            System.out.println("exception inside creating method");
            e.printStackTrace();
        }

        return new TableSchema(123, "thisIsAMistake", new ArrayList<>(), new Integer[123]);
    }

    /**
     * setting the tables for the catalog
     *
     * @param tables list of tables
     */
    public void setTables(ArrayList<TableSchema> tables) {
        this.tables = tables;

        for (TableSchema t : tables)
        {
            this.tableNameToTableSchema.put(t.getName(), t);
        }
    }

    /**
     * Catalog is formatted as
     * 4 Bytes - number of tables in the database
     *
     * @return a byte array
     */
    public byte[] createBytesForCatalog() {
        // adding in the header for the file
        byte[] bytes = new byte[0];
        byte[] numOfTables = Type.convertIntToByteArray(this.tables == null ? 0 : tables.size());

        bytes = Type.concat(bytes, numOfTables);

        for (TableSchema t : this.tables) {
            bytes = Type.concat(bytes, t.convertTableObjectToBytes());
        }

        return bytes;
    }

    /**
     * Writes byte array to the Catalog file
     * @param bytes
     */
    public void writeBytesToCatalogFile(byte[] bytes) {
        try {
            String catalogPath = this.path;
            if (path.contains("\\")) {
                catalogPath += "\\Catalog";
            } else {
                catalogPath += "/Catalog";
            }

            File file = new File(catalogPath);
            raf = new RandomAccessFile(file, WRITE);

            // every time we write to the catalog
            // we have to make sure that everything we are rewriting is updated
            raf.seek(0);
            raf.setLength(0);

            raf.write(bytes);

            raf.close();
        } catch (IOException e) {
            System.out.println("Couldn't write catalog to file.");
            e.printStackTrace();
        }
    }

    /**
     * creates an actual table file in the database that we write to later
     * @param tableName
     */
    public void createTableFile(String tableName)
    {
        File dir = new File(path);
        File file = new File(path + "/" + tableName);
        RandomAccessFile rand = null;
        dir.mkdirs();
        try {
            file.createNewFile();
            rand = new RandomAccessFile(file, "rw");
            rand.writeInt(0); // this table has 0 pages when first created
            rand.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets every single page id from every table schema from the catalog
     * @return combined arraylist of all page IDs
     */
    public ArrayList<Integer> getAllPageIDsFromAllTables()
    {
        ArrayList<Integer> ints = new ArrayList<>();

        for (TableSchema ts : this.tables)
        {
            ints.addAll(ts.getPageIds());
        }

        return ints;
    }
}
