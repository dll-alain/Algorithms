package HuffmanTree;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

/**
 * @author dll
 * @date 20220421
 */
public class HuffmanCode {

    /**
     * 1 将Huffman编码存储在Map<Byte, String>.
     * 32 -> 01 97 -> 100.
     * 2 生成Huffman编码表示，需要去拼接，定义一个StringBuilder 存储某个叶子节点的路径
     */
    private static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    private static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 1 利用huffmanCodes将ASCII 转化为Huffman对应的字符串
     * 2 此时字符串已经被Huffman编码为二进制，位数为133，比原来字符串40还长，因此需要转化为byte[]
     * 每八位二进制转化为一个数
     *
     * @param bytes        原始字符串对应的byte[]
     * @param huffmanCodes 生成Huffman编码的map
     * @return 返回Huffman编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmanCodes将ASCII 转化为Huffman对应的字符串
        StringBuilder stringBuilder2 = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder2.append(huffmanCodes.get(b));
        }
        //此时字符串已经被Huffman编码为二进制，位数为133，比原来字符串40还长，因此需要转化为byte[]
        // int len = (stringBuilder2.length() + 7) / 8;
        int len;
        if (stringBuilder2.length() % 8 == 0) {
            len = stringBuilder2.length() / 8;
        } else {
            len = stringBuilder2.length() / 8 + 1;
        }
        //创建转化后的存储数组
        byte[] huffmanCodeByte = new byte[len];
        //记录第几个byte
        int index = 0;
        for (int i = 0; i < stringBuilder2.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder2.length()) {
                //取到最后
                strByte = stringBuilder2.substring(i);
            } else {
                //substring 包前不包后
                strByte = stringBuilder2.substring(i, i + 8);
            }
            huffmanCodeByte[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeByte;
    }

    private static Map<Byte, String> getCodes(WeightNode root) {
        if (root == null) {
            return null;
        }
        getCodes(root, "", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将传入node的所有叶子节点的Huffman编码得到，并放入HuffmanCodes集合
     *
     * @param node          传入节点
     * @param code          左0右1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(WeightNode node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null) {
            if (node.data == null) {
                //判断当前node是否是非叶子结点,如果是则向递归处理
                getCodes(node.left, "0", stringBuilder1);
                getCodes(node.right, "1", stringBuilder1);
            } else {
                //当遇到叶子结点，说明该字符已经编码完毕
                huffmanCodes.put(node.data, stringBuilder1.toString());
            }
        }
    }

    /**
     * 将byte[] 转化为 node list
     * @param bytes 接受字节数组
     * @return 返回Node List
     */
    private static List<WeightNode> getNodes(byte[] bytes) {
        //1 创建一个ArrayList
        ArrayList<WeightNode> nodes = new ArrayList<WeightNode>();
        //2 遍历bytes， 统计每一个byte出现的次数
        Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
        int i = 1;
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //3 遍历map， 把键值对转化为node，加入到nodes里
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new WeightNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private static WeightNode creatHuffmanTree(List<WeightNode> nodes) {
        while (nodes.size() > 1) {
            //排序从小到大
            Collections.sort(nodes);
            WeightNode leftNode = nodes.get(0);
            WeightNode rightNode = nodes.get(1);
            //创建新的二叉树，没有data，只有权值
            WeightNode parent = new WeightNode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将合成的两个二叉树删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入
            nodes.add(parent);
        }
        //nodes最后只有一个节点就是Huffman的根节点
        return nodes.get(0);
    }

    private static void precedingOrder(WeightNode root) {
        if (root != null) {
            root.precedingOrder();
        } else {
            System.out.println("Huffman🌲为空无需遍历!");
        }
    }

    /**
     * main方法封装
     *
     * @param bytes 原始字符串对应的字节数组
     * @return 压缩后的数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        //1 得到输入字符串的ASCII码串
        //byte[] bytes = str.getBytes();
        //2 得到node list
        List<WeightNode> nodes = getNodes(bytes);
        //3 创建huffmanTree
        WeightNode huffmanRoot = creatHuffmanTree(nodes);
        //4 根据HuffmanTree进行编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanRoot);
        //5 根据HuffmanCode对原始数组进行压缩
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }


    public static void main(String[] args) throws IOException {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
//        System.out.println(Arrays.toString(contentBytes));
//        System.out.println(contentBytes.length);
//        List<WeightNode> nodes = getNodes(contentBytes);
//        System.out.println("node = " + nodes);
//        WeightNode root = creatHuffmanTree(nodes);
//        //precedingOrder(root);
//        getCodes(root);
//        System.out.println("Huffman编码表 " + huffmanCodes);

        byte[] huffmanCodeByte = huffmanZip(contentBytes);
        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeByte));

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        String gama = numberFormat.format(((float) 1 - (float) huffmanCodeByte.length / (float) contentBytes.length) * 100);
        System.out.println("压缩率 = " + gama + "%");

        byte[] sourceByte = huffmanDecode(huffmanCodes, huffmanCodeByte);
        System.out.println("解码后字符串 = " + new String(sourceByte));

//        //测试压缩文件
//        String srcFile1 = "/Users/d/Documents/IdeaProjects/DSAndAlgorithms/Huffman/zip/lux.jpg";
//        String dstFile1 = "/Users/d/Documents/IdeaProjects/DSAndAlgorithms/Huffman/zip/lux.zip";
//        zipFile(srcFile1, dstFile1);
//
        String srcFile2 = "/Users/d/Documents/IdeaProjects/DSAndAlgorithms/Huffman/zip/ENN.pdf";
        String dstFile2 = "/Users/d/Documents/IdeaProjects/DSAndAlgorithms/Huffman/zip/ENN.zip";
        zipFile(srcFile2, dstFile2);
        System.out.println("压缩成昆");

        //测试解压文件
        String zipF = "/Users/d/Documents/IdeaProjects/DSAndAlgorithms/Huffman/zip/ENN.zip";
        String dstF = "/Users/d/Documents/IdeaProjects/DSAndAlgorithms/Huffman/zip/afterHuffmanZipENN.pdf";
        unZipFile(zipF, dstF);
        System.out.println("解压成昆, 歪日");
    }


    /**
     * huffman 解码
     * 现将HuffmanByte数组转化为二进制字符串
     *
     * @param b    输入的Huffman编码.
     * @param flag true 前面length -1 位，需要截取八位, false 表示最后一位, 无需截取.
     * @return 返回二进制字符串
     */
    private static String byteToBiteString(byte b, boolean flag) {
        //将string转化为int
        int temp = b;
        if (flag) {
            //temp 按位或  256 = 1 0000 0000
            //eg 1 = 1 \ 1 0000 0000 = 1 0000 0001
            //若是负数，或后无影响,因为绝对值不大于255
            temp |= 256;
        }
        //toBinaryString放回的是int的二进制补码, 一个int4个字节, 32位，
        //负数默认32位，我们需要截取八位
        //正数是多少位就是多少位，|256后为九位，我们需要截取8位
        String str = Integer.toBinaryString(temp);

        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * @param huffmanCodes Huffman编码表map
     * @param huffmanBytes HuffmanBytes Huffman编码的字节数组
     * @return 放回最初的字符串
     */
    private static byte[] huffmanDecode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1 先得到HuffmanBytes 对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBiteString(b, !flag));
        }
        //System.out.println(stringBuilder.toString());

        //字符串按照指定的Huffman编码进行解码
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //System.out.println(map);
        List<Byte> list = new ArrayList<Byte>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //i 不动， count移动
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            //i 直接到count记忆的位置
            i += count;
        }
        //for循环结束，list已经存放了所有的字符
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 进行文件压缩
     * @param srcFile 希望压缩文件的路劲
     * @param dstFile 压缩后文件存放到哪个路径
     */
    public static void zipFile(String srcFile, String dstFile) throws IOException {
        //创建文件输入流
        OutputStream os = null;
        FileInputStream is = null;
        ObjectOutputStream oos = null;
        try  {
            is = new FileInputStream(srcFile);
            //创建一个和源文件一样大小的byte[]
            byte[] b = new byte[is.available()];
            is.read(b);
            //直接对文件进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建一个文件输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个对象输出流，写入Huffman编码，是为了以后我们恢复文件时使用
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            //huffmanCode 也需要写进去,为了以后的decode
            oos.writeObject(huffmanCodes);
            is.close();
            os.close();
            oos.close();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    /**
     * 对文件进行解压
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压的目标路径
     */
    public static void unZipFile(String zipFile, String dstFile) throws IOException {
        //定义文件输入流
        InputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        //定义文件输出流
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(zipFile);
            objectInputStream = new ObjectInputStream(inputStream);
            //读取Huffman编码数组
            byte[] huffmanBytes = (byte[]) objectInputStream.readObject();
            //读取Huffman编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) objectInputStream.readObject();
            //解码
            byte[] bytes = huffmanDecode(huffmanCodes, huffmanBytes);
            outputStream = new FileOutputStream(dstFile);
            outputStream.write(bytes);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                outputStream.close();
                objectInputStream.close();
                inputStream.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }


}

class WeightNode implements Comparable<WeightNode> {
    //存放数据的ASCII a = 97
    Byte data;
    int weight;
    WeightNode left;
    WeightNode right;

    public WeightNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void precedingOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.precedingOrder();
        }
        if (this.right != null) {
            this.right.precedingOrder();
        }
    }

    @Override
    public int compareTo(WeightNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "WeightNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}





