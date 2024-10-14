#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

pthread_mutex_t mutex;
int cnt=0;

void *count(void *arg){
    int i;
    char* name = (char*)arg;

    pthread_mutex_lock(&mutex);

    //======== critical section =============
    //cnt=0; 공유 자원
    for (i = 0; i <5; i++)
    {
        printf("%s cnt: %d\n", name,cnt);
        cnt++;
        
    }
    //========= critical section ============
    sleep(1);
    pthread_mutex_unlock(&mutex);
    
}

int main()
{
    pthread_t thread1,thread2,thread3,thread4;

    pthread_mutex_init(&mutex,NULL);
    pthread_create(&thread4, NULL, count, (void *)"thread4");
    
    pthread_create(&thread2, NULL, count, (void *)"thread2");
    pthread_create(&thread3, NULL, count, (void *)"thread3");

    pthread_create(&thread1, NULL, count, (void *)"thread1");
    //pthread_create(&thread2, NULL, count, (void *)"thread2");
   // pthread_create(&thread3, NULL, count, (void *)"thread3");
    //pthread_create(&thread4, NULL, count, (void *)"thread4");

    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);
    pthread_join(thread3, NULL);
    pthread_join(thread4, NULL);

    pthread_mutex_destroy(&mutex);
}

