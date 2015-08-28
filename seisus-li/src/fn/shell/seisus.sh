#!/bin/sh
# startup script for seisus
#-----------------------------------------------------------------------------
# Execute
#-----------------------------------------------------------------------------
case "`uname`" in
     FreeBSD*)
	JAVACMD="${JAVACMD:-"/usr/local/bin/java"}"
	;;
    *) 
	JAVACMD=`which java 2>/dev/null`
	;;
esac

if [ ! -x "${JAVACMD}" ]; then
    echo "Error: ${JAVACMD} is not present on your system" >&2
    echo "       Please specify the path to an existing java executable with the" >&2
    echo "       JAVACMD environment variable when running this." >&2
    exit 1
fi

exec "${JAVACMD}" -jar "@project.build.finalName@.jar" $@
