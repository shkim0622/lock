import threading
import time

lock1 = threading.Lock()

def func(name):
    lock1.acquire()  

    #======== critical section =============
    cnt = 0
    for i in range(5):
        print(name + " cnt:", cnt)
        cnt += 1
    #========= critical section ============
    
    time.sleep(1)
    lock1.release() 
    
thread1 = threading.Thread(target=func, args=("thread1",))
thread2 = threading.Thread(target=func, args=("thread2",))

thread1.start()
thread2.start()

thread1.join()
thread2.join()
