#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy html
#echo "begin copy html "
#cp -r ../ruoyi-ui/dist/** ./nginx/html/dist


# copy jar
codeUrl=/home/java/workspace/gjgs-dc

#echo "begin copy oo-gateway "
#cp ../ruoyi-gateway/target/ruoyi-gateway.jar ./ruoyi/gateway/jar

echo "begin copy oo-gjgs-dc-auth "
cp ${codeUrl}/oo-auth/target/oo-gjgs-dc-auth.jar ./oo/auth/jar

#echo "begin copy oo-visual "
#cp ../ruoyi-visual/oo-monitor/target/ruoyi-visual-monitor.jar  ./ruoyi/visual/monitor/jar

echo "begin copy oo-gjgs-dc-system "
cp ${codeUrl}/oo-modules/oo-system/target/oo-gjgs-dc-system.jar ./oo/modules/system/jar

echo "begin copy oo-gjgs-dc-file "
cp ${codeUrl}/oo-modules/oo-file/target/oo-gjgs-dc-file.jar ./oo/modules/file/jar

echo "begin copy oo-gjgs-dc-job "
cp ${codeUrl}/oo-modules/oo-job/target/oo-gjgs-dc-job.jar ./oo/modules/job/jar

#echo "begin copy oo-modules-gen "
#cp ../ruoyi-modules/oo-gen/target/ruoyi-modules-gen.jar ./ruoyi/modules/gen/jar

echo "begin copy oo-gjgs-dc-datamanagement "
cp ${codeUrl}/oo-modules/oo-datamanagement/target/oo-datamanagement.jar ./oo/modules/datamanagement/jar

echo "begin copy oo-gjgs-dc-reportforms "
cp ${codeUrl}/oo-modules/oo-reportforms/target/oo-reportforms.jar ./oo/modules/reportforms/jar

