This is my first pipeline in Concourse.
I will run Coucourse with Docker.

# What is Concourse CI
https://concourse-ci.org/

Official says like this.
> You can think of a pipeline as a distributed, higher-level, continuously-running Makefile.


# Easy to Run Concourse with Docker 🐳

```
$ wget https://concourse-ci.org/docker-compose.yml
$ docker-compose up -d
```

By default `wget` command not installed in Mac.
You can use `curl` command.

```
$ curl -O https://concourse-ci.org/docker-compose.yml
```

## install fly cli

1. Open http://localhost:8080/
2. Click your OS icon to download `fly`
3. Move `fly` to your local path.

```
$ sudo mv ~/Downloads/fly /usr/local/bin
$ sudo chmod 0755 /usr/local/bin/fly
$ fly -v 
```

# Login concourse with fly
You can access your Concourse CI on `http://localhost:8080`.
dowlaod `fly` CLI.

## fly login

```
$ fly -t tutorial login -c http://localhost:8080 -u test -p test
```

## fly set-pipeline

```
fly -t tutorial set-pipeline \
  --pipeline hello-world-pipeline \
  --config hello.yml
```
You can see first pipeline and job in the pipeline.
Start job in manual with click ➕ icon.

# [WIP] How to isntall on Mac
1. Donwload binary

How to install other environment
