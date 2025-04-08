Great topic, Shilpa! Let's go **in-depth** into **Multi-threading in Java** 🚀

---

# 🔥 **1. What is Multi-threading in Java?**

> **Multi-threading** is the ability to run multiple parts of a program **simultaneously** to **increase efficiency and performance**.

✅ **Key Benefits of Multi-threading**
- **Better CPU Utilization** 🖥️
- **Faster Execution** ⏩
- **Improved Responsiveness** (useful in GUI applications)
- **Parallel Processing**

---

# 🎯 **2. Key Concepts in Multi-threading**

| Concept | Description |
|---------|-------------|
| **Thread** | A lightweight subprocess that runs independently. |
| **Process vs Thread** | A process has **its own memory**, while threads **share memory**. |
| **Concurrency vs Parallelism** | Concurrency = handling multiple tasks at once (not necessarily simultaneously). Parallelism = truly executing multiple tasks at once. |
| **Thread Lifecycle** | New → Runnable → Running → Blocked → Terminated. |
| **Synchronization** | Prevents multiple threads from accessing shared resources at the same time. |

---

# 🏗 **3. Creating Threads in Java**

There are **two main ways** to create threads in Java:

## ✅ A. **Extending `Thread` Class**
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // Start a new thread
    }
}
```
> **Pros**: Easy to implement  
> **Cons**: Cannot extend any other class (since Java does not support multiple inheritance).

---

## ✅ B. **Implementing `Runnable` Interface**
```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

public class RunnableExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        t1.start();
    }
}
```
> **Pros**: More flexible (can extend another class).  
> **Cons**: Requires a `Thread` instance to start execution.

---

# 🔄 **4. Thread Lifecycle**

| State | Description |
|-------|-------------|
| **New** | Thread is created but not started. |
| **Runnable** | Ready to run, waiting for CPU. |
| **Running** | Currently executing. |
| **Blocked** | Waiting for a resource (like a lock). |
| **Terminated** | Execution is completed or stopped. |

---

# ⚙ **5. Thread Methods**

| Method | Description |
|--------|-------------|
| `start()` | Starts a thread. |
| `run()` | Defines the thread's task. |
| `sleep(ms)` | Pauses the thread for the given milliseconds. |
| `join()` | Waits for a thread to complete before continuing. |
| `yield()` | Hints to the scheduler to give other threads a chance to execute. |
| `setPriority(int)` | Sets thread priority (`1 to 10`). |
| `isAlive()` | Checks if the thread is still running. |

### ✅ **Example: Using `join()`**
```java
class MyThread extends Thread {
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
        }
    }
}

public class JoinExample {
    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        t1.start();
        t1.join();  // Main thread waits for t1 to finish
        System.out.println("Main thread resumes");
    }
}
```

---

# 🔐 **6. Synchronization (Handling Shared Resources)**

**Problem:** When multiple threads try to access a shared resource, **data inconsistency** can occur.

### ✅ **Solution: Use `synchronized` keyword**
```java
class Counter {
    private int count = 0;

    public synchronized void increment() { // Thread-safe method
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class SyncExample {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> { for (int i = 0; i < 1000; i++) counter.increment(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 1000; i++) counter.increment(); });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final Count: " + counter.getCount()); // Expected: 2000
    }
}
```
> **The `synchronized` keyword ensures only one thread can modify `count` at a time.**

---

# 🚀 **7. Thread Communication (`wait()`, `notify()`, `notifyAll()`)**

✅ **Why?**  
Threads sometimes need to communicate with each other, e.g., a **producer-consumer** problem.

### ✅ **Example: Using `wait()` & `notify()`**
```java
class SharedResource {
    private boolean available = false;

    public synchronized void produce() throws InterruptedException {
        while (available) {
            wait(); // Wait until consumed
        }
        System.out.println("Produced an item");
        available = true;
        notify(); // Notify the consumer
    }

    public synchronized void consume() throws InterruptedException {
        while (!available) {
            wait(); // Wait until produced
        }
        System.out.println("Consumed an item");
        available = false;
        notify(); // Notify the producer
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producer = new Thread(() -> {
            try { resource.produce(); } catch (InterruptedException e) {}
        });

        Thread consumer = new Thread(() -> {
            try { resource.consume(); } catch (InterruptedException e) {}
        });

        producer.start();
        consumer.start();
    }
}
```
> **`wait()` makes a thread wait until another thread calls `notify()`.**

---

# ⚡ **8. Thread Pools (`ExecutorService`)**
Creating too many threads can **degrade performance**. Instead, we use **Thread Pools** to manage a **fixed number of worker threads**.

### ✅ **Using `ExecutorService`**
```java
import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            executor.execute(() -> System.out.println("Task executed by: " + Thread.currentThread().getName()));
        }

        executor.shutdown(); // Stop accepting new tasks
    }
}
```
> **Efficient resource management using a fixed thread pool.**

---

# 📌 **9. Deadlocks & Avoidance**
**Deadlock** occurs when two threads are waiting for each other's resources, causing a freeze.

### ❌ **Example of Deadlock**
```java
class Resource {
    synchronized void methodA(Resource other) {
        System.out.println(Thread.currentThread().getName() + " locked methodA");
        other.methodB();
    }
    
    synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + " locked methodB");
    }
}

public class DeadlockExample {
    public static void main(String[] args) {
        Resource r1 = new Resource();
        Resource r2 = new Resource();

        new Thread(() -> r1.methodA(r2)).start();
        new Thread(() -> r2.methodA(r1)).start();
    }
}
```
> **To avoid deadlocks, use proper lock ordering or `ReentrantLock`.**

---

# 🚀 **10. Summary Table**
| Concept | Description |
|---------|-------------|
| **Thread Creation** | `extends Thread` or `implements Runnable` |
| **Thread Lifecycle** | New → Runnable → Running → Blocked → Terminated |
| **Synchronization** | `synchronized` keyword to prevent race conditions |
| **Inter-thread Communication** | `wait()`, `notify()`, `notifyAll()` |
| **Thread Pool** | `ExecutorService` for efficient thread management |
| **Deadlocks** | Occur when two threads wait for each other's lock |

---

## 🚀 **Next Steps**
Would you like to explore **Advanced Concurrency, Atomic Variables, or Parallel Streams?** 🔥

### **Producer-Consumer Problem Using `ArrayList` in Java (Manual Synchronization)**
Since `ArrayList` is **not thread-safe**, we need to manually handle synchronization using `synchronized`, `wait()`, and `notify()`.

---

## ✅ **Solution Using `ArrayList`**
Here, we use:
- **A shared `ArrayList<Integer>`** to store produced items.
- **`wait()`**: Makes the consumer **wait** if the list is empty.
- **`notify()`**: Wakes up the waiting producer/consumer when needed.

---

### ✅ **Code Implementation**
```java
import java.util.ArrayList;
import java.util.List;

class Producer implements Runnable {
    private final List<Integer> list;
    private final int CAPACITY;

    public Producer(List<Integer> list, int capacity) {
        this.list = list;
        this.CAPACITY = capacity;
    }

    @Override
    public void run() {
        int value = 1;
        try {
            while (true) {
                synchronized (list) {
                    while (list.size() == CAPACITY) { // Wait if the list is full
                        System.out.println("Producer waiting - List is full");
                        list.wait();
                    }
                    System.out.println("Produced: " + value);
                    list.add(value++);
                    list.notify(); // Notify consumer that new item is available
                }
                Thread.sleep(500); // Simulate delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private final List<Integer> list;

    public Consumer(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (list) {
                    while (list.isEmpty()) { // Wait if the list is empty
                        System.out.println("Consumer waiting - List is empty");
                        list.wait();
                    }
                    int item = list.remove(0);
                    System.out.println("Consumed: " + item);
                    list.notify(); // Notify producer that space is available
                }
                Thread.sleep(1000); // Simulate processing time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProducerConsumerArrayList {
    public static void main(String[] args) {
        List<Integer> sharedList = new ArrayList<>();
        int CAPACITY = 3; // Maximum size of the list

        Thread producerThread = new Thread(new Producer(sharedList, CAPACITY));
        Thread consumerThread = new Thread(new Consumer(sharedList));

        producerThread.start();
        consumerThread.start();
    }
}
```

---

## 🔍 **Explanation**
1. **Producer** adds items to the list **until full** (`list.size() == CAPACITY`).
    - Calls `list.wait()` if full.
    - Calls `list.notify()` after adding an item.
2. **Consumer** removes items from the list **until empty**.
    - Calls `list.wait()` if empty.
    - Calls `list.notify()` after consuming an item.
3. **Synchronization** ensures proper coordination.
    - **Avoids race conditions**.
    - **Handles waiting and notifying manually**.

---

## ✅ **Sample Output**
```
Produced: 1
Produced: 2
Produced: 3
Producer waiting - List is full
Consumed: 1
Produced: 4
Consumed: 2
Produced: 5
Consumed: 3
Produced: 6
Producer waiting - List is full
```
- Producer **stops producing** when the list is full.
- Consumer **waits if the list is empty**.
- `wait()` and `notify()` **prevent busy waiting**.

---

## ⚡ **Key Takeaways**
| **Approach** | **Pros** | **Cons** |
|-------------|---------|---------|
| **ArrayList + wait/notify** | More control over synchronization | Manual synchronization is complex |
| **BlockingQueue** | Automatic handling of waiting and notifying | Less customization |

🔹 **For simpler code, prefer `BlockingQueue`**, but **for learning and control, use `ArrayList` with `wait()` & `notify()`**.

---

## 🚀 **Next Steps**
Would you like to extend this with **multiple producers and consumers** or **using `ReentrantLock` instead of `synchronized`**? 🚦

### **Producer-Consumer Problem with a List (BlockingQueue) in Java**

The **Producer-Consumer Problem** is a classic synchronization problem where:
- **Producer** adds items to a shared list.
- **Consumer** removes items from the shared list.
- Proper synchronization is required to avoid race conditions.

---

## ✅ **Solution Using `BlockingQueue` (Best Practice)**
Using `BlockingQueue` (`LinkedBlockingQueue`) simplifies synchronization because it **automatically handles waiting and notification**.

### 🔹 **Key Features of `BlockingQueue`:**
- `put(item)`: Blocks if the queue is full.
- `take()`: Blocks if the queue is empty.

---

### ✅ **Code Implementation**
```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Produced: " + i);
                queue.put(i); // Adds item to queue (waits if full)
                Thread.sleep(500); // Simulate delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer item = queue.take(); // Retrieves & removes (waits if empty)
                System.out.println("Consumed: " + item);
                Thread.sleep(1000); // Simulate processing time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProducerConsumerBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3); // Capacity = 3

        Thread producerThread = new Thread(new Producer(queue));
        Thread consumerThread = new Thread(new Consumer(queue));

        producerThread.start();
        consumerThread.start();
    }
}
```

---

## **🔍 Explanation**
1. **Producer** puts elements into the queue using `put()`.
2. **Consumer** retrieves elements using `take()`.
3. `BlockingQueue` **handles synchronization automatically**, so no need for `wait()` and `notify()`.
4. The **queue size is limited (3 items)**, preventing overproduction.

---

## **🛠 Output Example**
```
Produced: 1
Consumed: 1
Produced: 2
Produced: 3
Consumed: 2
Produced: 4
Consumed: 3
Produced: 5
Consumed: 4
Consumed: 5
```
- Producer produces **faster** (500ms delay).
- Consumer consumes **slower** (1000ms delay).
- Queue automatically **handles waiting** if full/empty.

---

## ✅ **Alternative: Using `wait()` and `notify()`**
Would you like to see a **manual implementation with `wait()` and `notify()`** instead of `BlockingQueue`?

### **Producer-Consumer with Multiple Producers & Consumers using `ArrayList` and `ReentrantLock`** 🚀

Since `ArrayList` **is not thread-safe**, we will use **`ReentrantLock`** instead of `synchronized` for better control over thread execution.

---

## ✅ **Why Use `ReentrantLock` Instead of `synchronized`?**
🔹 Provides **more flexibility** with methods like `lock()`, `unlock()`, and `tryLock()`.  
🔹 Avoids **thread starvation** by allowing **fair locking** (FIFO scheduling).  
🔹 Supports **multiple condition variables** (`await()`, `signal()`, `signalAll()`).

---

## ✅ **Code Implementation with Multiple Producers & Consumers**
```java
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedResource {
    private final List<Integer> list = new ArrayList<>();
    private final int CAPACITY;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public SharedResource(int capacity) {
        this.CAPACITY = capacity;
    }

    public void produce(int value) throws InterruptedException {
        lock.lock(); // Acquire lock
        try {
            while (list.size() == CAPACITY) {
                System.out.println(Thread.currentThread().getName() + " waiting - List is full");
                notFull.await(); // Wait until space is available
            }
            list.add(value);
            System.out.println(Thread.currentThread().getName() + " Produced: " + value);
            notEmpty.signal(); // Notify consumers
        } finally {
            lock.unlock(); // Release lock
        }
    }

    public void consume() throws InterruptedException {
        lock.lock(); // Acquire lock
        try {
            while (list.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " waiting - List is empty");
                notEmpty.await(); // Wait until items are available
            }
            int item = list.remove(0);
            System.out.println(Thread.currentThread().getName() + " Consumed: " + item);
            notFull.signal(); // Notify producers
        } finally {
            lock.unlock(); // Release lock
        }
    }
}

class Producer implements Runnable {
    private final SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        int value = 1;
        try {
            while (true) {
                resource.produce(value++);
                Thread.sleep(500); // Simulate production delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private final SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            while (true) {
                resource.consume();
                Thread.sleep(1000); // Simulate consumption delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProducerConsumerReentrantLock {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource(3); // Shared list with capacity 3

        Thread producer1 = new Thread(new Producer(resource), "Producer-1");
        Thread producer2 = new Thread(new Producer(resource), "Producer-2");
        Thread consumer1 = new Thread(new Consumer(resource), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(resource), "Consumer-2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
```

---

## 🔍 **Explanation**
1. **Multiple Producers & Consumers** work on a shared `ArrayList<Integer>`.
2. **ReentrantLock** is used for manual thread synchronization.
    - `lock.lock()` → Acquires lock
    - `lock.unlock()` → Releases lock
3. **Condition Variables** handle waiting & notifying efficiently.
    - `notFull.await()` → Producer waits when the list is full.
    - `notEmpty.await()` → Consumer waits when the list is empty.
    - `notFull.signal()` → Wakes up a waiting producer.
    - `notEmpty.signal()` → Wakes up a waiting consumer.

---

## ✅ **Sample Output (Multiple Producers & Consumers Running in Parallel)**
```
Producer-1 Produced: 1
Producer-2 Produced: 2
Producer-1 Produced: 3
Producer-1 waiting - List is full
Consumer-1 Consumed: 1
Producer-1 Produced: 4
Consumer-2 Consumed: 2
Producer-2 Produced: 5
Consumer-1 Consumed: 3
Consumer-2 waiting - List is empty
```
- **Producers stop when the list is full** (`CAPACITY=3`).
- **Consumers wait if the list is empty**.
- `signal()` wakes up the appropriate waiting thread.

---

## ⚡ **Key Advantages of `ReentrantLock`**
| Feature | `synchronized` | `ReentrantLock` |
|---------|--------------|----------------|
| Fine-grained control | ❌ No | ✅ Yes |
| Explicit lock/unlock | ❌ No | ✅ Yes |
| Multiple conditions | ❌ No | ✅ Yes (`await()`, `signal()`) |
| Try-lock without waiting | ❌ No | ✅ Yes (`tryLock()`) |
| Fair locking | ❌ No | ✅ Yes (`new ReentrantLock(true)`) |

---

## 🚀 **Next Steps**
Would you like to extend this with:
- **Thread Pool (`ExecutorService`)** for better thread management?
- **Fair Locking (`new ReentrantLock(true)`)** to prevent starvation?
- **Using `Semaphore` instead of locks for bounded buffer control?**

Let me know what you'd like to dive into next! 🔥