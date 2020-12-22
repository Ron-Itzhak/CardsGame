package Objects;

import java.util.ArrayList;

public class DB {
    ArrayList<Record> records;
    private final int MAX_SIZE=10;
    public DB(){
        records = new ArrayList<>();
    }


    public Boolean insert_Record(Record record){
        if (records.size()==0){
            records.add(record);
            return true;
        }
        for (int i =0;i<records.size();i++){
                if (record.getScore()>records.get(i).getScore()) {
                    records.add(i, record);
                    if (records.size()>MAX_SIZE){
                        records.remove(MAX_SIZE);}
                return true;
                }
        }
        return false;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

}

