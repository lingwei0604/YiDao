for((i=1;i<9;i++))
do
    for((j=1;j<=i;j++))
    do
        let "temp=i*j"
        echo "$i*$j = & temp"
    done
done  