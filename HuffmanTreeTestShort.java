import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanTreeTestShort {
    @Test
    public void nametest2() {
        Name evaluator = new Name();
        assertEquals("", evaluator.toString());
    }

    @Test
    public void nametest3() {
        Name oneName = new Name("Olivia", 17535.0, "F");
        Name evaluator = new Name(oneName);
        assertEquals("Olivia", evaluator.toString());
    }

    @Test
    public void compresstest1() {
        Name oneName = new Name("Olivia", 17535.0, "M");
        Compress evaluator = new Compress(oneName, "/*/*//*");
        assertEquals("Olivia\t/*/*//*", evaluator.toString());
    }


    @Test
    public void compresstest4() {
        ArrayList<Compress> evaluator = new ArrayList<>();
        evaluator.add(new Compress(new Name(":", 111111.0, "none"), "*"));
        evaluator.add(new Compress(new Name("Amelia", 12704.0, "F"), "**/**//*"));
        evaluator.add(new Compress(new Name("Olivia", 17535.0, "F"), "/*/*//*"));
        evaluator.add(new Compress(new Name("Ava", 13084.0, "F"), "***///**"));
        evaluator.add(new Compress(new Name("Benjamin", 12136.0, "M"), "**//*///"));
        evaluator.add(new Compress(new Name("Charlotte", 13003.0, "F"), "***/////"));
        evaluator.add(new Compress(new Name("Elijah", 13034.0, "M"), "***////*"));
        evaluator.add(new Compress(new Name("Alexander", 10151.0, "M"), "*//*/**/"));
        evaluator.add(new Compress(new Name("Henry", 10705.0, "M"), "*/*//**/"));
        evaluator.add(new Compress(new Name("Liam", 19659.0, "F"), "*///*//"));
        evaluator.add(new Compress(new Name("Mia", 11157.0, "F"), "*/**/*//"));
        Compress.bubbleSort(evaluator);

        String output = "";
        for (Compress oneName : evaluator)
            output += oneName.getName() + "-";

        assertEquals("Amelia-Ava-Benjamin-Charlotte-Elijah-Alexander-Henry-Mia-Olivia-Liam-:-", output);

    }

    @Test
    void HuffmanTreetest1() throws FileNotFoundException {
        HuffmanTree evaluator = new HuffmanTree("names2020_10.csv");
        assertEquals("::Liam:Noah:Olivia:Emma:Oliver:Ava:Elijah:Charlotte:Sophia:William:", evaluator.toString());
    }


    @Test
    public void HuffmanTreetest4() throws FileNotFoundException {
        HuffmanTree evaluator = new HuffmanTree("names2020_20.csv");
        ArrayList<Compress> compressNames = evaluator.compressCodes();
        Compress.bubbleSort(compressNames);
        assertEquals("Michael\t/**///", compressNames.get(0).toString());
    }


    @Test
    public void HuffmanTreetest7() throws FileNotFoundException {
        HuffmanTree evaluator = new HuffmanTree("names2020.csv");
        ArrayList<Compress> compressNames = evaluator.compressCodes();
        Compress.bubbleSort(compressNames);
        assertEquals("Liam\t*///*//", compressNames.get(compressNames.size() - 1).toString());

    }
    @Test
    public void encodetest1() throws FileNotFoundException {
        HuffmanTree evaluator = new HuffmanTree("names2020.csv");
        ArrayList<Compress> compressNames = evaluator.compressCodes();
        assertEquals("*/*///*//***", evaluator.encode("Zyaire"));
    }

    @Test
    public void encodetest2() throws FileNotFoundException {
        HuffmanTree evaluator = new HuffmanTree("names2020.csv");
        ArrayList<Compress> compressNames = evaluator.compressCodes();
        assertEquals("*****//*/*", evaluator.encode("Aaliyah"));
    }
    @Test
    public void encodetest3() throws FileNotFoundException {
        HuffmanTree evaluator = new HuffmanTree("names2020.csv");
        ArrayList<Compress> compressNames = evaluator.compressCodes();
        assertEquals("//**///*/*****//*/**////**/**/*/", evaluator.encode("Zavier:Alexander:Tanner"));
    }


    @Test
    public void encodetest6() throws FileNotFoundException {
        assertThrows(InvalidHuffmanCodeException.class, () -> {
            HuffmanTree evaluator = new HuffmanTree("names2020_20.csv");
            ArrayList<Compress> compressNames = evaluator.compressCodes();
            evaluator.encode("Liam-Olivia");
        });
    }

    @Test
    public void encodetest8() throws FileNotFoundException {
        assertThrows(InvalidHuffmanCodeException.class, () -> {
            HuffmanTree evaluator = new HuffmanTree("names2020_20.csv");
            ArrayList<Compress> compressNames = evaluator.compressCodes();
            evaluator.encode("");
        });
    }

    @Test
    public void encodetest9() throws FileNotFoundException {
        assertThrows(InvalidHuffmanCodeException.class, () -> {
            HuffmanTree evaluator = new HuffmanTree("names2020_20.csv");
            ArrayList<Compress> compressNames = evaluator.compressCodes();
            evaluator.encode(":");
        });
    }
     @Test
    public void decodetest2() throws FileNotFoundException {
        HuffmanTree evaluator = new HuffmanTree("names2020.csv");
        ArrayList<Compress> compressNames = evaluator.compressCodes();
        assertEquals("Eleanor:Elijah:Emma", evaluator.decode("**/**////***////*///****"));
    }


    @Test
    public void decodetest7() throws FileNotFoundException {
        assertThrows(InvalidHuffmanCodeException.class, () -> {
            HuffmanTree evaluator = new HuffmanTree("names2020_20.csv");
            ArrayList<Compress> compressNames = evaluator.compressCodes();
            evaluator.decode("~");
        });
    }

}