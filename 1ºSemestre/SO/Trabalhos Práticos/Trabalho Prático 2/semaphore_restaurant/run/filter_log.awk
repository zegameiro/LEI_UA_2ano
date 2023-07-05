BEGIN {
     FS = " ";
     f=1
     #chef
     FieldSize[f++]=3;
     #waiter
     FieldSize[f++]=2;
     #passengers
     FieldSize[f++]=4;
     for(i=1;i<20;i++) {
        FieldSize[f++] = 3;
     }
     #stats
     FieldSize[f++]=4;
     for(i=1;i<4;i++) {
        FieldSize[f++] = 3;
     }
}

/.*/ {
    if(NF==26 && $1!="CH") {
#        print  "NOTFILTE " $0
        for(i=1; i<=26; i++) {
            if(i<24) {
               if($i==prev[i]) {
                 printf("%*s ",FieldSize[i],".")
               }
               else printf ("%*s ",FieldSize[i],$i) 
               prev[i] = $i
            }
            else {
               printf ("%*s ",FieldSize[i],$i) 
            }
        }
        printf("\n")
    }
    else print $0
}

END{
}
