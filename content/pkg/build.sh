set -eu
if [ "$OSTYPE" == "msys" ] || [ "$OSTYPE" == "cygwin" ]; 
then 
    sam="sam.cmd"
else
    sam="sam"
fi
eval $sam build --cached --parallel --region $2 ##--use-container
cd ../../engine
mvn clean process-resources -DkskipTests
rm -rf ../content/pkg/.aws-sam/build/DassanaEngine/content
rm -rf ../content/pkg/.aws-sam/build/DassanaEngineApi/content
cp -R ./target/classes/content ../content/pkg/.aws-sam/build/DassanaEngine/content/
cp -R ./target/classes/content ../content/pkg/.aws-sam/build/DassanaEngineApi/content/

cd ../content/pkg
eval $sam package -t .aws-sam/build/template.yaml --s3-bucket $1 --region $2 --output-template-file uploaded-template.yaml
eval $sam deploy --template-file uploaded-template.yaml --stack-name $3  --capabilities CAPABILITY_NAMED_IAM  CAPABILITY_AUTO_EXPAND --region $2
