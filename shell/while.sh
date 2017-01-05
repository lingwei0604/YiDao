i=1
while ((i<=9))
do
    for ((j=1;j<=i;j++))
    do
       let "temp=i*j"
       echo -n  "$i*$j=$temp "
    done
    let i++
    echo ""
done