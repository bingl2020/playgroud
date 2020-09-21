package OOD;
/*
Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.

readContentFromFile: Given a file path, return its content in string format.

 

Example:

Input:
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/design-in-memory-file-system
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


 */
import java.util.*;

class FileSystem {
    private class File{
        /* 记录文件中的内容，如果是文件夹则内容恒为空字符串 */
        public String content;

        /* 子文件夹或是子文件名称与对应File Object的映射 */
        public Map<String, File> allFile = new HashMap<>();

        /* 记录当前File Object属于文件还是文件夹 */
        public boolean isFolder;

        public File(boolean folder){
            isFolder = folder;
            content = "";
        }
    }
    private File root;
    public FileSystem() {
        root = new File(true);
    }

    public List<String> ls(String path) {
        String str[] = path.split("/"); //去除path字符串的首个"/"并将"/"作为分割符获得一个字符串数组
        File current = root;
        for(int i=1; i<str.length; i++){
            if(current.allFile.containsKey(str[i])) current = current.allFile.get(str[i]);
        }
        List<String> lsAns = new ArrayList<>();
        if(!current.isFolder){
            lsAns.add(str[str.length-1]);
        }
        else{
            /* 如果ls operation操作对象是一个文件夹，则将其子文件/文件夹表复制到新的数组并按字典序排序 */
            String copy[] = new String[current.allFile.size()];
            int i=0;
            for(String s : current.allFile.keySet()){
                copy[i] = s;
                i++;
            }
            Arrays.sort(copy, new Comparator<String>(){
                public int compare(String a, String b){
                    return a.compareTo(b);
                }
            });
            for(int j=0; j<copy.length; j++){
                lsAns.add(copy[j]);
            }
        }
        return lsAns;
    }

    public void mkdir(String path) {
        String str[] = path.substring(1).split("/");
        File current = root;
        for(int i=0; i<str.length; i++){
            if(!current.allFile.containsKey(str[i])){
                current.allFile.put(str[i], new File(true));
            }
            current = current.allFile.get(str[i]);
        }
        return;
    }

    public void addContentToFile(String filePath, String content) {
        String str[] = filePath.split("/");
        File current = root;
        for(int i=1; i<str.length; i++){
            if(!current.allFile.containsKey(str[i])){
                current.allFile.put(str[i], new File(false));
            }
            current = current.allFile.get(str[i]);
        }
        StringBuilder sb = new StringBuilder().append(current.content).append(content);
        current.content = sb.toString();
        return;
    }

    public String readContentFromFile(String filePath) {
        String str[] = filePath.substring(1).split("/");
        File current = root;
        for(int i=0; i<str.length; i++){
            current = current.allFile.get(str[i]);
        }
        return current.content;
    }
}

