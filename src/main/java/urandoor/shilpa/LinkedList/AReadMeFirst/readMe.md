## Types of Declarations of an Array:

![img_1.png](images/img_1.png)

Vector and ArrayList are dynamic size arrays . They grow automatically.

Problems of Arrays :
Either size is fixed and pre-allocated or worst case insertion at the end is tita(n)
insertion in the middle of beginning is costly

![img_2.png](images/img_2.png)

deletion from middle or beginning is also very costly
implementation of data structures like queue and dequeue is complex with arrays
because in queue - insertion happens from one end, and deletion happens from other end

![img_3.png](images/img_3.png)

with arrays implementing round robin scheduling is very difficult

![img_4.png](images/img_4.png)

this is another problem where its very complex to implement via arrays

![img_5.png](images/img_5.png)

we will alwways need an auxillary space to solve this. Using arrays its very difficult. if we use linked list, 
we do not need extra space


# **Linked List**

also a linear data structure
stores item in sequential manner one after the other
the idea is to drop contiguous memory allocation so that insertions and deletions can efficiently happen at the middle
or beginning also and no need to preallocate the space - so no extra space is consumed

👉 Linked List అనేది nodes ద్వారా కలిపిన డేటా స్ట్రక్చర్.

👉 ప్రతి Node దగ్గర data మరియు next pointer ఉంటుంది.

👉 Array కన్నా ఇది డైనమిక్ గాను, మెమరీని సమర్థవంతంగా ఉపయోగించుకునేలా ఉంటుంది.


![img.png](images/img_6.png)

Single linked list implementation in Java

![img.png](img.png)

memory allocation can be random . Does not have to be side by side

✔ ప్రతి Node దగ్గర data మరియు next pointer ఉంటుంది.

✔ Nodes లింక్ చేయాలి → first.next = second;

✔ Linked List ను ప్రింట్ చేయాలి → while(temp != null) {}

👉 ఇది పూర్తిగా చేతితో (manually) Nodes ను లింక్ చేసే implementation.


Basic Node [Creation](../SinglyLinkedList/SLLNode.java)


[link to program](../SinglyLinkedList/BasicImplementation.java)

![img_1.png](img_1.png)

int data - here we assume that data is integer, it can be string or any class student etc.
Node next - self reference structure. it has reference to self type
then we have constructor - data is x - passed parameter and next is null

first node will be called head, and it stores the reference of temp1 which is 2nd node.
so first all the nodes are created, then they are linked here
but not an idle way. just a static example.

![img_2.png](img_2.png)

## **Traversing a singly Linked List**


![img_3.png](img_3.png)

we will keep printing the current data and keep moving to the next of current

![img_4.png](img_4.png)

time complexity : O(n)
we will go through the whole Linked List

same problem recursively

![img_5.png](img_5.png)

time complexity : O(n)
requires auxillary space of n+1 because its recursive . it needs to store the previous function stack to return back.
thats why iterative is better than recursive here.

[Link to program](../SinglyLinkedList/SLLTraversal.java)


## **Insertion at the beginning of Singly Linked List**

![img_6.png](img_6.png)

[Link to program](../SinglyLinkedList/SLLInsertAtTheBeginning.java)

First we allocate memory to the new node
point the next reference to head and return

![img_7.png](img_7.png)

## **Insertion at the end of singly linked list** 

[link to program](../SinglyLinkedList/SLLInsertAtTheEnd.java)

![img_8.png](img_8.png)

first allocate memory to the last node

![img_9.png](img_9.png)


![img_10.png](img_10.png)

**Insert at given position in single linked list**

[Link to Program](../SinglyLinkedList/SLLInsertAtPosition.java)

![img_11.png](img_11.png)


## **Delete First or head node of SLL**

![img_12.png](img_12.png)

![img_13.png](img_13.png)


[link to the program ](../SinglyLinkedList/SLLDeleteHead.java)


## **Delete Last Node of SLL**

![img_14.png](img_14.png)

![img_15.png](img_15.png)

[Link to the program](../SinglyLinkedList/SLLDeleteTail.java)


## **Search the position of the given key in SLL**

![img_16.png](img_16.png)

**iterative approach :**

![img_17.png](img_17.png)

**recursive approach :**

![img_18.png](img_18.png)

[ link to program  ](../SinglyLinkedList/SLLSearch.java) 



## **Doubly Linked list**

![img_19.png](img_19.png)








































































