#!/bin/bash
#ident   "%W%"

if [ "${TPS_ENV}x" == "x" ]; then
        echo "TPS_ENV is not set!"
        exit 1
fi

usage="usage: ${0} -t <tps_data_file_name> -s <simpliciti_data_file_name>"

javaArgs=
TPS_DATA_FILE=
SIMPLICITI_DATA_FILE=

while getopts t:s:h opt
do
	case $opt in
	t) TPS_DATA_FILE=$OPTARG;;
	s) SIMPLICITI_DATA_FILE=$OPTARG;;
	h) echo $usage; exit 1;;
	*) echo $usage; exit 1;;
	esac
done

if [ "$TPS_DATA_FILE" == "" ];
then
  echo $usage
  exit 1
fi

if [ "$SIMPLICITI_DATA_FILE" == "" ];
then
  echo $usage
  exit 1
fi


javaArgs="-tps ${TPS_DATA_FILE} -simpliciti ${SIMPLICITI_DATA_FILE}"

currentEnv=$(echo $TPS_ENV | grep -o "[^-]*")
RATES_HOME=/opt/tpsd/auto-${currentEnv}/rates/install
AUTOSYS_LOG_DIR=/opt/tpsd/log/autosys
export LD_LIBRARY_PATH=/opt/tpsd/markitwirelib:/opt/tpsd/lib:/opt/tpsd/auto-${currentEnv}/coreSvc/install/lib/risk-lib/fpml-lib:/opt/tpsd/orainstclient/10.2l64

if [ "${currentEnv}" == "PROD" ]; then
  JAVA_HOME=/opt/tpsd/java/1.6.0_13l64/
  RATES_HOME=/opt/tpsd/tpsderiv-svcs/rates/install
  export LD_LIBRARY_PATH=/opt/tpsd/markitwirelib:/opt/tpsd/lib:/opt/tpsd/tpsderiv-svcs/coreSvc/install/lib/risk-lib/fpml-lib:/opt/tpsd/orainstclient/10.2l64
fi

cmdName=rates-reset-svc-exe
pushd ../../../$cmdName

cp ./classes/triOptima/* ./
cp -fR ../conf/gemfire ./classes
cp -f ../conf/gemfire/gemfireLicense.zip ./classes
cp -fR ../conf/gemfire.properties ./classes



export CLASSPATH=$(cat classpath-unix.txt)
echo "Running Rates TriOptima Report Script with $javaArgs"

TSTAMP=`date +%F.%N`

java -Xmx1024m -Xloggc:DM_GC.log -XX:MaxPermSize=256m -Drates.reset.dsp.enable=false -classpath $CLASSPATH com.citi.ficc.rates.reset.trioptima.CombineTPSAndSimplicitiDataFile $javaArgs > ${AUTOSYS_LOG_DIR}/combineTPSAndSimplicitiDataFile-${TSTAMP}.log 2>&1 &

exit 0 
