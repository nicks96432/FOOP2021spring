# Design Report

> Please follow the instructions in homework 3 (officially announced version on NTU COOL) to finish the report.

## Software Design

### 主要流程

我把所有功能都放在`class Directory`內，並在`class Main`中傳進去  
所有代表功能的class都繼承`abstract class DirFunction`，  
並且會實作`getPerformType()`和`perform()`兩個method  
分別代表找出這個功能要使用的對象和對找到的對象使用功能  
在`FileSystemCLI()`讀到指令後會對現在所在的資料夾使用`callFunction()`並把指令和參數傳進去  
`callFunction()`會找出這個資料夾是否有相對應的功能，如果沒有就丟Exception  
丟出的Exception在`FileSystemCLI()`會被接住

### class介紹

`class Main`：程式開始的地方，如果要新增功能可以在這裡使用`suppNodeFunctions.add(new 新功能());`增加  
`class FileSystemCLI`：整個程式主要的部份，會在讀入指令後使用對應的功能  
`class Node`：檔案系統中的節點，檔案、資料夾和連結都繼承這個class  
`class Directory`：代表資料夾的class，繼承`class Node`  
`class DirectoryFactory`：因為我覺得每次都要把`class Directory`有的功能傳進去很麻煩，所以寫了這個class，只要呼叫他的static mathod`newDirectory()`就可以建立新的資料夾  
`class File`：代表檔案的class，繼承`class Node`  
`class Link`：代表連結的class，繼承`class Node`  
`abstract class DirFunction`：代表在資料夾內可以做的事，所有的功能都會繼承這個class  
`class CatFile`：可以對檔案進行`cat`的功能  
`class CatLink`：可以對連結進行`cat`的功能  
`class CdDirectory`：可以`cd`進資料夾的功能  
`class CdLink`：可以`cd`進連結的功能  
`class LnDirectory`：可以用`ln`建立一個資料夾的連結的功能  
`class LsDirectory`：可以在資料夾內進行`ls`的功能  
`class Mkdir`：可以用`mkdir`建立新資料夾的功能  
`class RmDirectory`：可以用`rm`刪除資料夾的功能  
`class RmFile`：可以用`rm`刪除檔案的功能  
`class RmLink`：可以用`rm`刪除連結的功能  
`class Search`：可以用`search`的功能
`class Touch`：可以用`touch`建立檔案的功能

### 整體架構簡圖

![UML](https://imgur.com/z5co810.png)
