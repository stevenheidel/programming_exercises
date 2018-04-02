fn lookandsay(vec: Vec<u8>) -> Vec<u8> {
    let capacity = (vec.len() as f64 * 1.303).ceil() as usize;
    let mut res = Vec::with_capacity(capacity);
    let mut count = 0;
    let mut num = 0;
    for x in vec {
        if num == 0 {
            num = x;
            count = 1;
        } else if x == num {
            count += 1;
        } else {
            res.push(count);
            res.push(num);
            num = x;
            count = 1;
        }
    }
    res.push(count);
    res.push(num);
    return res;
}

fn lookandsay_inplace(arr: &mut [u8], res: &mut [u8], n: usize) -> usize {
    let mut j = 0;
    let mut count = 0;
    let mut num = 0;
    for i in 0..n {
        let x = arr[i];
        if num == 0 {
            num = x;
            count = 1;
        } else if x == num {
            count += 1;
        } else {
            res[j] = count; j += 1;
            res[j] = num; j += 1;
            num = x;
            count = 1;
        }
    }
    res[j] = count; j += 1;
    res[j] = num; j += 1;
    return j;
}

fn print_array(arr: &mut Vec<u8>, n: usize) {
    for i in 0..n {
        print!("{}", arr[i]);
    }
    println!();
}

fn main_inplace() {
    let size = 100_000_000_000;
    let mut arr = vec![0 as u8; size];
    let mut res = vec![0 as u8; size];
    arr[0] = 1;

    let mut j = 1;
    for i in 0..100 {
        println!("{}: {}", i, j);
        // print_array(&mut arr, j);
        j = lookandsay_inplace(&mut arr, &mut res, j);

        let temp = arr;
        arr = res;
        res = temp;
    }
}

fn main() {
    let mut vec = vec![1];
    for i in 0..100 {
        println!("{}: {:?}", i, vec.len());
        let vec_len = vec.len() as f64;
        let res = lookandsay(vec);
        println!("{}", res.len() as f64 / vec_len);
        vec = res;
    }
}
