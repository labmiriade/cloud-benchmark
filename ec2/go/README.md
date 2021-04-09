# Go version

Per compilare:

```shell
GOOS=linux GOARCH=amd64 go build
```

Per buildare per ARM utilizzare GOARCH=arm64.

Per buildare con _jsoniter_ usare:

```shell
GOOS=linux GOARCH=amd64 go build -tags=jsoniter
```

