# fetch-rewards challenge

Receipt Processor API

## Build

```
docker build -t 'receipt-processor' --target prod .
```

## Run

```
docker run -p 8080:8080 receipt-processor
```

The APIs will be available at `http://localhost:8080/`

## Debug

Instead of building the image with target `prod`, use the build target `debug`.

```
docker build -t 'receipt-processor' --target debug .
```

```
docker run -p 8080:8080 -p 5005:5005 receipt-processor
```

The APIs will be available at `http://localhost:8080`, and the remote JVM debugger can be attached to port `5005`.
